#!/bin/bash
set -e

confd --onetime --backend env

if [ "${1:0:1}" = "" ]; then
  exec telegraf --config /telegraf/conf/telegraf.conf >> /telegraf/logs/telegraf.log 2>&1
else
  exec "$@"
fi
