#!/usr/bin/env bash

rm -f *.jar *.class 2>/dev/null
javac JPackageDemo.java
cat JPackageDemo.mf
jar cmf JPackageDemo.mf JPackageDemo.jar JPackageDemo.class JPackageDemo.java
cd ..
rm -rf JPackageDemo.app JPackageDemo-*.dmg 2>/dev/null
jpackage --name JPackageDemo --input jpackage-demo --main-jar JPackageDemo.jar --type dmg
cd jpackage-demo
rm -f *.jar *.class 2>/dev/null
