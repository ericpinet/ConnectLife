#!/bin/bash

# Ensure that the previous compilation was clean.
rm -R gen-java/

# run thrift to generate source file.
thrift -r --gen java ConnectLifeApi.thrift

# Copy source file in right place in project.
cp -R ./gen-java/com/ ../src/main/java/com/

