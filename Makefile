

api/java/target/govhack2016-java-api.jar: api/java/
	mkdir -p /tmp/m2/repository
	mkdir -p /tmp/java-api
	cd api/java && docker build -t java-api-builder .
	docker run -t -i -v /tmp/m2/repository:/tmp/m2/repository -v /tmp/java-api:/tmp/java-api java-api-builder
	cp -r /tmp/java-api/target .

java-api-service: api/java/target/govhack2016-java-api.jar

all: java-api-service

run: java-api-service
