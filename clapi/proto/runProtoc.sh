#!/bin/bash

#protoc --java_out=../src/main/java/ clapi.proto

protoc --plugin=protoc-gen-grpc-java=/Users/ericpinet/github/grpc-java/compiler/build/binaries/java_pluginExecutable/protoc-gen-grpc-java \
  --grpc-java_out="../src/main/java/" --proto_path="./" "clapi.proto"
