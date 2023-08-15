# Demo despliegue de Wordpress

### Variables utiles 
```
export OPENSHIFT_USER=
```

### Crear base de datos mariadb para wordpress
```
oc new-app mariadb-ephemeral
```

### Crear app wordpress vacia, se debe configurar desde cero
```
oc new-app php~https://github.com/wordpress/wordpress
```
obtendras una salida similar a la que esta a continuación, guardar credenciales
```
* With parameters:
    * Memory Limit=512Mi
    * Namespace=openshift
    * Database Service Name=mariadb
    * MariaDB Connection Username=user4GK # generated
    * MariaDB Connection Password=fnVDqVi06Lwx2PBu # generated
    * MariaDB root Password=XrscqSrQbOfkx3UY # generated
    * MariaDB Database Name=sampledb
    * Version of MariaDB Image=10.3-el8
```

### Obtener URL del Registry Publico de la imagen wordpress que se creo recientemente
```
OCP_REGISTRY_URL=$(oc get is wordpress -o jsonpath='{.status.publicDockerImageRepository}') 
```

### Login en Registry de Openshift
```
podman login \
    ${OCP_REGISTRY_URL} \
    --username %OPENSHIFT_USER% \
    --password "$(oc whoami -t)" 
```

