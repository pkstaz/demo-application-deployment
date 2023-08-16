
oc apply -f tasks/hello.yaml
tkn task start --showlog hello


oc create -f tasks/apply_manifest_task.yaml
oc create -f tasks/update_deployment_task.yaml

oc create -f resources/persistent_volume_claim.yaml

oc create -f pipelines/pipeline.yaml


tkn pipeline start build-and-deploy -w name=shared-worksp
pvc -p deployment-name=pipelines-vote-api -p git-url=https://github.com/openshift/pipeline
s-vote-api.git -p IMAGE=image-registry.openshift-image-registry.svc:5000/user1/vote-api --
showlog


tkn pipeline start build-and-deploy -w name=shared-workspace,claimName=source-pvc -p deployment-name=pipelines-vote-ui -p git-url=https://github.com/openshift/pipelines-vote-ui.git -p IMAGE=image-registry.openshift-image-registry.svc:5000/user1/vote-ui --showlog




oc delete pipeline build-and-deploy && \
oc delete task hello && \
oc delete task apply-manifests && \
oc delete task update-deployment 