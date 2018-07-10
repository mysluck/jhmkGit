#!/usr/bin/env bash

cd `dirname $0`;
cd ..


if [ ! -d "logs" ]; then
mkdir logs
echo 'Logs directory create.'
fi

echo 'Start anytxn-cms web service.'

nohup mvn spring-boot:run -Dfork=true >> logs/console_out.log &

sleep 2

tail -f logs/console_out.log


