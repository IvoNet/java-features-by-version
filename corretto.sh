#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Please provide the version you want to demo..."
  echo "Please not that corretto does not support all the versions of Java."
  echo "e.g."
  echo "./corretto.sh 11"
  exit 1
fi

if [ "$1" == "16" ]; then
  docker run -it --rm -v "$(pwd)/src:/src" ivonet/corretto:$1 /bin/sh -c "cd /src/java$1;ls;pwd;export JAVA_OPTS='--enable-preview --source $1';export PS1='$ ';java -version;exec /bin/sh"
else
  docker run -it --rm -v "$(pwd)/src:/src" amazoncorretto:$1-alpine-full /bin/sh -c "cd /src/java$1;ls;pwd;export JAVA_OPTS='--enable-preview --source 11';export PS1='$ ';java -version;exec /bin/sh"
fi


