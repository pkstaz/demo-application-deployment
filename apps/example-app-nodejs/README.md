# Build & Publish an Image

## Publicar imagen en Docker

### Requisitos
- Asegurate de tener una cuenta en [docker.com](https://docker.com/)
- Podman

### Variables de entorno

    $ export DOCKER_USER=<your-docker.io-user>
    $ export QUAY_USER=<your-quay.io-user>


### Login on Docker
    $ podman login docker.io -u $DOCKER_USER
    # Password: <your-docker-pass>
### Build Docker Image
Primero debes construir la imagen localmente, para luego ser enviada a tu espacio docker. 

    $ podman build . -t docker.io/$DOCKER_USER/example-app-nodejs:0.0.1

    # STEP 1/7: FROM node:16
    # ? Please select an image: 
    #    registry.fedoraproject.org/node:16
    #    registry.access.redhat.com/node:16
    #    registry.centos.org/node:16
    #  ▸ docker.io/library/node:16

> **_NOTE:_**  For this example we have used the docker image.

    STEP 1/7: FROM node:16
    ✔ docker.io/library/node:16
    Trying to pull docker.io/library/node:16...
    Getting image source signatures
    Copying blob b6013b3e77fe done  
    ···
    STEP 7/7: CMD [ "node", "index.js" ]
    COMMIT <DOCKER_USER>/example-app-nodejs:0.0.1
    --> 8e9501c55b2
    Successfully tagged localhost/cestay/example-app-nodejs:0.0.1
    8e9501c55b273ee388ce75297dc891a0cb70133ae03569667d1c8de030bc169c

check your image in localhost repository 

    $ podman image list

    # REPOSITORY                           TAG         IMAGE ID      CREATED         SIZE
    # localhost/cestay/example-app-nodejs  0.0.1       8e9501c55b27  12 minutes ago  933 MB
    # docker.io/library/node               16          842962c4b3a7  7 days ago      928 MB

you can see your build image and base node image used for build your image
### Push Image on Docker
    $ podman push docker.io/$DOCKER_USER/example-app-nodejs:0.0.1

    # Getting image source signatures
    # Copying blob 034b01b1f757 done
    # ···
    # Copying config d971563c87 done  
    # Writing manifest to image destination
    # Storing signatures

now you can go to your docker account and you should see the image published.

![Publised image on docker](../.resources/img/docker_published_image.png)
![Publised image on docker with details](../.resources/img/docker_published_image_with_details.png)






## QUAY
    $ podman login quay.io

    $ podman build . -t quay.io/$QUAY_USER/example-app-nodejs:0.0.1

    $ podman push quay.io/$QUAY_USER/example-app-nodejs:0.0.1
    # Getting image source signatures
    # Copying blob 034b01b1f757 done  
    # Copying blob 79a45871588c done  
    # ···
    # Writing manifest to image destination
    # Copying config 275fe93f69 
    # Writing manifest to image destination
    # Storing signatures

[![Docker Repository on Quay](https://quay.io/repository/cestayg/example-app-nodejs/status "Docker Repository on Quay")](https://quay.io/repository/cestayg/example-app-nodejs)