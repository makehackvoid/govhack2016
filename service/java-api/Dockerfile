FROM maven:3-jdk-7

#USER www-data
RUN echo "Australia/Canberra" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

ADD . /usr/src/app/

ONBUILD RUN mvn install
CMD cd /usr/src/app/ && java -jar govhack2016-java-api.jar
