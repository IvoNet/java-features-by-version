#include <stdio.h>

//On macOS make sure you have the XCode installed and 'xcode-select --install' before:
//gcc -c -fpic helloworld.c
//gcc -shared -o helloworld.so helloworld.o

void helloworld() {
	printf("Hello Java 17 lovers!\n");
}
