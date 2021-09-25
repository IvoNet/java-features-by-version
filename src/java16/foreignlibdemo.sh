#!/usr/bin/env bash

# tested with the standard openjdk docker image (./openjdk.sh 16)

#apt-get update
#apt-get install -y gcc

gcc -c -fpic helloworld.c
gcc -shared -o helloworld.so helloworld.o

javac --add-modules jdk.incubator.foreign JEP389.java
java --add-modules jdk.incubator.foreign -Dforeign.restricted=permit JEP389
