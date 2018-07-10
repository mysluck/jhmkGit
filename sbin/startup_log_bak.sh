#!/bin/sh

#NOHUP日志文件的路径
NOHUP_DIR="/data/anytxn_prd_2017/logs"
time="`date +%Y%m%d%H%M%S`"
echo 'time :'$time

if ! [ -f $NOHUP_DIR/nohup.log ]
    then
    echo $NOHUP_DIR/nohup.log" file not exist!"
    exit 1
fi

if [ `ls -l $NOHUP_DIR/nohup.log | awk '{print $5}'` -gt $((20*1024*1000)) ] #大于 20M 一备份
    then
    echo 'backup old...'
    cat $NOHUP_DIR/nohup.log >> $NOHUP_DIR/nohup-$time.log # 将日志备份

    echo 'clear old...'
    cat /dev/null > $NOHUP_DIR/nohup.log #清空日志
fi
