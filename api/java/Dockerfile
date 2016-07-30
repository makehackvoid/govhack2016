FROM maven:3-jdk-7

#USER www-data
VOLUME /tmp/m2/repository
VOLUME /tmp/java-api

RUN ln -s /tmp/m2/repository ~/.m2/
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

ADD . /usr/src/app/

ONBUILD RUN mvn install
CMD cd /usr/src/app/ && mvn clean package && cp -r /usr/src/app/target /tmp/java-api/
