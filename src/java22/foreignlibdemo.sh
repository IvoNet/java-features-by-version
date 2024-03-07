#!/usr/bin/env bash

# tested with the standard openjdk docker image (./openjdk.sh 22)

# install gcc if not exists
if ! command -v gcc &> /dev/null
then
  apt-get update
  apt-get install -y gcc
fi

lib=calc

gcc -c -fpic $lib.c
gcc -shared -o $lib.so $lib.o

javac  JEP454.java
java --enable-native-access=ALL-UNNAMED JEP454

rm -f calc.o calc.so JEP454.class
