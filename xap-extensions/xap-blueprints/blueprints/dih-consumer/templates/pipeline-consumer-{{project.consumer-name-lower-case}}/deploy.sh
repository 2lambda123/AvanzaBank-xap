#!/usr/bin/env bash
set -e
GS_HOME=${GS_HOME=`(cd ../../../; pwd )`}
$GS_HOME/bin/gs.sh pu deploy pipeline-consumer-{{project.consumer-name-lower-case}} dih-consumer/target/{{project.consumer-name-lower-case}}-dih-consumer.war