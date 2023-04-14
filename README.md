# Exemplo de microserviços utilizando Eureka, RabbitMq, Api de clientes e cartões e Docker


## Criar network no docker para que os MS possam se comunicar 
docker network create cursoms-network

## Keycloak
docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.0.2 start-dev


## Subindo as imagens na rede  cursoms-network

## Rabbitmq
docker run -d --name cursoms-rabbitmq -p 5672:5672 -p 15672:15672 --network cursoms-network rabbitmq:3.9-management

## Eureka
docker run -d --name cursoms-eureka -p 8761:8761 --network cursoms-network cursoms-eureka

## MS Cartões
Gerar imagem cursoms-cartoes: docker build --tag cursoms-cartoes .

docker run -d --name cursoms-cartoes --network cursoms-network cursoms-cartoes

