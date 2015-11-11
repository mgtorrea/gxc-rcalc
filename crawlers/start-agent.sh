#!/bin/bash

# Starts the agent given by the configuration_file

CWD=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
cd $CWD

# check if flume-ng is on PATH
hash flume-ng 2>/dev/null || { echo >&2 "flume-ng is not installed or is not on your PATH env. Aborting."; exit 1; }

configuration_file='conf/test.properties'
agent_name='a1'

# for local data we need to create the directory
mkdir ../data

# start agent
flume-ng agent -n $agent_name -c conf -f $configuration_file