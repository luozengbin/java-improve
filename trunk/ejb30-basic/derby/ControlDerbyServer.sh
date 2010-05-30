#!/bin/sh

export DERBY_HOME=/home/akira/toolkit/jdk1.6.0_20/db
if [ $1 = "start" ]; then
	$DERBY_HOME/bin/startNetworkServer
else
	$DERBY_HOME/bin/stopNetworkServer
fi