# demo-application-deployment
 A continuacion se describen los pasos que te permitiran montar el ambiente necesario para la demo. 

> **_NOTE:_**  Puedes utilizar tu propia imagen sin la necesidad de utilizar la aplicacion de exemplo publicada en este repositorio.


1. [Compilar y publicar una imagen en](.apps/example-app-nodejs/README.md#publicar-imagen-en-docker) [docker.com](https://docker.com/)
2. Compilar y publicar una imagen en [quay.io](quay.io)
3. [Instalar ArgoCD](./operators/gitops/README.md)
4. Aplicar Configuracion Inicial
    ```bash
    oc apply -f ./initial-config
    ```
