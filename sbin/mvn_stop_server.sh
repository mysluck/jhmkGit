#!/usr/bin/env bash

cd `dirname $0`;
cd ..
mvn spring-boot:stop -Dfork=true
