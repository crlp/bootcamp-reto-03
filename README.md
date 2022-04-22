# bootcamp-reactive-webflux-full

# DATABASE
## Crear BD de MongoDB
`docker run -d -p 27016:27017 -v ~/data:/data/db --name mongo mongo`

# SECURITY
## Iniciar un servicio de Identity Management con Keycloak
`docker run -d --name start-keycloak -p 9999:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:17.0.0 start-dev`

## Tutorial para configurar una app en keycloak
https://medium.com/devops-dudes/securing-spring-boot-rest-apis-with-keycloak-1d760b2004e

# SPRING CLOUD STREAM

## Instalar docker-compose

https://docs.docker.com/compose/install/

## Crear kafka hub  with Docker-Compose
`docker-compose up -d`

## Eliminar contenedores Kafka
`docker-compose down`

## Tutorial Spring Cloud Stream
https://benwilcock.github.io/spring-cloud-stream-demo/