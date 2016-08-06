#!/bin/bash

xgettext -ktrc -ktr -kmarktr -ktrn:1,2 -o keys.pot $(find ../ -name "*.java")
