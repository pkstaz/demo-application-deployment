Crear imagen de contenedor
```
buildah bud -f Containerfile -t localhost/hello-world-php .
```

Iniciar contenedor en local
```
podman run -p 8085:80 localhost/hello-world-php
```

Sino se ha iniciado sesion en OCP, se debe hacer login
```
oc login...
```

Crear repositorio en registry de OCP
```
oc create imagestream hello-world-php 
```

Validar registry creado correctamente
```
oc get imagestream hello-world-php
```

Ejemplo de URL de Registry 
```
default-route-openshift-image-registry.apps.cluster-d7t7x.d7t7x.sandbox59.opentlc.com/namespace/hello-world-php
```

Obtener URL del registry para publicar la imagen
```
OCP_REGISTRY_URL=$(oc get imagestream hello-world-php \
    -o jsonpath='{.status.publicDockerImageRepository}')
```

Login en registry
```
podman login \
    ${OCP_REGISTRY_URL} \
    --username opentlc-mgr \
    --password "$(oc whoami -t)"
```

Crear tag y copiar la imagen en el registry
```
podman tag localhost/hello-world-php ${OCP_REGISTRY_URL}:latest 
```

publicar imagen en el registry
```
podman push ${OCP_REGISTRY_URL}:latest
```

Obtener URL privada del registry en OCP
```
CLUSTER_LOCAL_REGISTRY_URL=$(oc get imagestream \
    hello-world-php \
    -o jsonpath='{.status.dockerImageRepository}')
```

Iniciar pod desde imagen
```
oc run \
    hello-world-php \
    --image ${CLUSTER_LOCAL_REGISTRY_URL}:latest
```

Crear servicio
```
oc expose \
    --port 80 \
    pod/hello-world-php 
```

Crear ruta publica para consumir el servicio
```
oc expose svc hello-world-php
```