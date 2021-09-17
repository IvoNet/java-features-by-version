#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Please provide the version you want to demo..."
  echo "e.g."
  echo "jstart.sh 12"
  exit 1
fi

docker run -it --rm -v "$(pwd)/src/java$1:/src" openjdk:$1 /bin/bash -c "cd /src;ls;export JAVA_OPTS='--enable-preview --source $1';export PS1='$ ';java -version;exec /bin/bash"
