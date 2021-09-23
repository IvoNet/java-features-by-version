#include <stdio.h>

//On macOS make sure you have the XCode installed and 'xcode-select --install' before:
//gcc -c -fpic helloworld.c
//gcc -shared -o helloworld.so helloworld.o


//If you use my "jstart.sh 16" command you will get a clean docker image
//apt-get update
//apt-get install -y gcc
//gcc -c -fpic helloworld.c
//gcc -shared -o helloworld.so helloworld.o

void helloworld() {
	printf("Hello Java 16 lovers!\n");
}
