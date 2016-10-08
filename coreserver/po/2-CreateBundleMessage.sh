#!/bin/bash

msgfmt --java2 --verbose -d ../src/main/resources -r i18n.Messages -l en en_CA.po
msgfmt --java2 --verbose -d ../src/main/resources -r i18n.Messages -l fr fr_CA.po
