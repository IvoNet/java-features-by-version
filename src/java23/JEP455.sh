#!/usr/bin/env bash

JAR=json-20230618.jar
if [ ! -f $JAR ]; then
#  check if wget is installed otherwise install it
  if ! command -v wget &> /dev/null
  then
    apt update > /dev/null 2>&1
    apt install -y wget > /dev/null 2>&1
  fi
  wget -q https://repo1.maven.org/maven2/org/json/json/20230618/$JAR
fi

set -vx
java -cp ./$JAR --enable-preview --source 23  JEP455.java
set +vx
rm -rf *.class
#rm -f ./$JAR
