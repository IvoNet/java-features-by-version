# Java features by version

A lot of demo's of java features by version.

Important note!
If you open this project in an IDE like IntelliJ or Eclipse you will get lots 
of errors.
This comes from the fact that many features need a specific version of java or 
special options added to the commands.
the folders starting with java<version> are NOT packages but the base folder for the code within.

# Prerequisites

* [Docker](https://docs.docker.com/get-docker/) installed

# Usage

I have included two ways of trying out most of the code. 
I have no guaranties for any version before version 7 (1.7) as I could not find docker images for these
anymore.

You can try out the code with the amazon corretto images or the standard openjdk versions.
Some code will not work with either the one or the other as they may not have included some features in ther
distribution.

For version 16 I have included a special build of my onw based on the openjdk:16-slim build to inlcude the 
gcc compiler to test one of the features introduced in that version.


Start a specific version like this:

```shell
./openjdk.sh <version>
# or
./corretto.sh <version>
```

e.g. to start trying out the java 16 stuff

```shell
./openjdks.sh 16
# or
./corretto.sh 16
```

# Troubleshooting

If the scripts do not work you are either on a Windows machine, and you may have to adjust them (please make a Pull Request if you do) or the scripts are not executable.

Fix:
* open a shell in the projct folder
```shell
chmod +x *.sh
```
* Try again :-)



# License

    Copyright 2021 Ivo Woltring <WebMaster@ivonet.nl>
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

