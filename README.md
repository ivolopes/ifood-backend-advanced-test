## Passo a passo para rodar o projeto

O projeto roda com java 11.

Será necessário subir as aplicações na ordem abaixo e rodar os comandos na raiz dos projetos:

### 1 - Config-server

- ./mvnw clean install
- java -jar target/config-server-0.0.1-SNAPSHOT.jar

### 2 - service-discovery

- ./mvnw clean install
- java -jar target/service-discovery-0.0.1-SNAPSHOT.jar

### 3 - api-gateway

- ./mvnw clean install
- java -jar target/api-gateway-0.0.1-SNAPSHOT.jar

### 4 - tracing-server

- java -jar zipkin.jar

### 5 - securitylib

- ./mvnw clean install

### 6 - admin-service

- ./mvnw clean install
- java -jar admin-service-0.0.1-SNAPSHOT.jar

### 7 - climate-service

- ./mvnw clean install
- java -jar climate-service-0.0.1-SNAPSHOT.jar

### 8 - music-service

- ./mvnw clean install
- java -jar target/music-service-0.0.1-SNAPSHOT.jar

## Passo a passo para testar o projeto

#### 1 - Logar e gerar o token

- tipo: POST
- url: http://localhost:8080/admin-service/api/v1/login
- header: Content-Type - application/json
- body: {
	"username":"ivolopes",
	"password":"123456"
	}

O token fica no header Authorization do response

#### 2 - Pesquisar a lista de música

- tipo: GET
- url: localhost:8080/music-service/api/v1/tracks
- headers: Authorization
- parametros: city ou lat e lon
- exemplos: city=Cork / lat=51.9&lon=-8.47


	
