# Step by step

1. Install Red Hat OpenShift GitOps Operator
2. Create namespace for install ArgoCD    
    ```bash
    oc apply -f namespace.yaml
    ```
3. Create ArgoCD in new namespace (step 2). Using Red Hat OpenShift GitOps Operator.