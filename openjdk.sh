#!/usr/bin/env bash

if [ -z "$1" ]; then
  echo "Please provide the version you want to demo..."
  echo "Please note that openjdk does not support versions of java before 7."
  echo "e.g."
  echo "./openjdk.sh 20"
  exit 1
fi

if [ "$1" == "16" ]; then
  docker pull ivonet/openjdk:$1
  docker run -it --rm -v "$(pwd)/src:/src" ivonet/openjdk:$1 /bin/bash -c "cd /src/java$1;ls;pwd;export JAVA_OPTS='--enable-preview --source $1';export PS1='$ ';java -version;exec /bin/bash"
else
  docker pull openjdk:$1-slim
#  docker run -it --rm -p 8888:8888 -v "$(pwd)/src:/src" openjdk:$1-slim /bin/bash -c "cd /src/java$1;ls;pwd;export JAVA_OPTS='--enable-preview --source $1';echo \"export PS1='$ '\" >>/root/.bashrc;printenv;java -version;exec /bin/bash"
  docker run -it --rm  -v "$(pwd)/src:/src" openjdk:$1-slim /bin/bash -c "cd /src/java$1;ls;pwd;export JAVA_OPTS='--enable-preview --source $1';echo \"export PS1='$ '\" >>/root/.bashrc;printenv;java -version;exec /bin/bash"
fi


