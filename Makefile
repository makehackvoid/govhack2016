api/java/target/govhack2016-java-api.jar: api/java/src/* $(shell find api/java/src -type f)
	mkdir -p /tmp/m2/repository
	mkdir -p /tmp/java-api
	cd api/java && docker build -t java-api-builder .
	docker run -t -i -v /tmp/m2/repository:/tmp/m2/repository -v /tmp/java-api:/tmp/java-api java-api-builder
	cp -r /tmp/java-api/target api/java/

service/java-api/govhack2016-java-api.jar: api/java/target/govhack2016-java-api.jar
	cp api/java/target/govhack2016-java-api.jar service/java-api/govhack2016-java-api.jar

service/java-api/mhvapp.properties:
	cp api/java/target/mhvapp.properties service/java-api/mhvapp.properties

java-api-service: service/java-api/govhack2016-java-api.jar service/java-api/mhvapp.properties service/java-api/Dockerfile
	cd service/java-api && docker build -t java-api .

all: java-api-service
