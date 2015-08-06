# Pull base image.
FROM ubuntu:latest

# Expose ports.
EXPOSE 8080

# Initial update
RUN apt-get update

#common files
RUN apt-get install -y software-properties-common

#Get repositories for java8
RUN echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
RUN echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
RUN apt-get update

#Install JDK 8
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections
RUN apt-get install oracle-java8-installer -y

# Install Redis-Server
RUN apt-get install -y redis-server

# Install maven
RUN apt-get update
RUN apt-get install -y maven

# Prepare by downloading dependencies
ADD URLShortener/pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

WORKDIR /code

#create the start server file and make it executable
RUN echo '#!/bin/bash' >> /start
RUN echo 'mvn package' >> /start
RUN echo 'java -jar target/urlshortener-0.0.1-SNAPSHOT.jar' >> /start
RUN chmod 755 /start

RUN mv /start /code