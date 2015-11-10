#!/bin/bash

# Builds and installs the flume plugin on $FLUME_HOME/plugins.d

CWD=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

# check if FLUME_HOME exists
if [ -z ${FLUME_HOME+x} ]; then 
	echo "FLUME_HOME is not set. Install flume and set FLUME_HOME to it's installation directory"; 
	exit 1;
else 
	echo "FLUME_HOME=$FLUME_HOME"
	FLUME_PLUGINS="$FLUME_HOME/plugins.d"
	mkdir $FLUME_PLUGINS
fi


installPlugin() {
	echo "Installing flume plugin $(ls target/*.gz) to target directory: $FLUME_PLUGINS"
	cp "$(ls target/*.gz)" $FLUME_PLUGINS && \
	tar -xzvf "$(ls $FLUME_PLUGINS/*.gz)" -C $FLUME_PLUGINS && \
	rm "$(ls $FLUME_PLUGINS/*.gz)"
}

cd $CWD
if ls target/*.gz 1> /dev/null 2>&1; then
	installPlugin
else
	echo "Building flume plugin"
	mvn clean package && \
	installPlugin
fi

