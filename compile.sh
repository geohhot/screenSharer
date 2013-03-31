#!/bin/bash

javac -classpath lib/commons-codec-1.7.jar:lib/json-simple-1.1.1.jar:. src/*.java

mv src/*.class ./
