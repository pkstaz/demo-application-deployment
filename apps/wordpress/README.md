# Demo despliegue de Wordpress

### Variables utiles 
export OPENSHIFT_USER=

### Crear base de datos mariadb para wordpress
oc new-app mariadb-ephemeral

### Crear app wordpress vacia, se debe configurar desde cero
oc new-app php~https://github.com/wordpress/wordpress

#### 

### Obtener URL del Registry Publico de la imagen wordpress que se creo recientemente
OCP_REGISTRY_URL=$(oc get is wordpress -o jsonpath='{.status.publicDockerImageRepository}') 

### 

### 
podman login \
    ${OCP_REGISTRY_URL} \
    --username %OPENSHIFT_USER% \
    --password "$(oc whoami -t)" 
