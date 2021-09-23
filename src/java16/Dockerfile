FROM openjdk:16-slim

RUN apt-get update \
 && apt-get install -y gcc \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

