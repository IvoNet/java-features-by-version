#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Please provide the version you want to demo..."
  echo "Please not that openjdk does not support versions of java before 7."
  echo "e.g."
  echo "./openjdk.sh 12"
  exit 1
fi

if [ "$1" == "16" ]; then
  docker run -it --rm -v "$(pwd)/src/java$1:/src" ivonet/openjdk:$1 /bin/bash -c "cd /src;ls;export JAVA_OPTS='--enable-preview --source $1';export PS1='$ ';java -version;exec /bin/bash"
else
  docker run -it --rm -v "$(pwd)/src/java$1:/src" openjdk:$1-slim /bin/bash -c "cd /src;ls;export JAVA_OPTS='--enable-preview --source $1';export PS1='$ ';java -version;exec /bin/bash"
fi


