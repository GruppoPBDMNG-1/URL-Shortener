# Pull base image.
FROM ubuntu:latest

# Expose ports.
EXPOSE 8080 6379

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

# Install curl utility just for testing
RUN apt-get update && \
	apt-get install -y curl
	
#Install nano - file editor
RUN apt-get install nano

# Install Redis-Server
RUN apt-get install -y redis-server

#Install Maven
RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-3.3.3 /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

# Prepare by downloading dependencies
ADD URLShortener/pom.xml /code/pom.xml
ADD URLShortener/src /code/src
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

WORKDIR /code

#create the start server file and make it executable
RUN echo '#!/bin/bash' >> /start
RUN echo 'mvn package' >> /start
RUN echo 'java -jar target/urlshortener-0.0.1-SNAPSHOT.jar' >> /start
RUN chmod 755 /start

#RUN mv /start /code