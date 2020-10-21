#!/bin/bash

tag_repo=`cd /root/Sirius.Vault.Guardian && git pull && git describe --tag`
tag_docker=`echo $tag_repo |awk -F- '{print $2}'`

#changing image tag
sed -E -i'' "s/(image: swisschains\/sirius-vault-guardian).*/image: swisschains\/sirius-vault-guardian:s390x-$tag_docker/" '/root/docker/docker-compose.yaml'
#pull and restart containier
docker-compose -f /root/docker/docker-compose.yaml pull
docker-compose -f /root/docker/docker-compose.yaml down
docker-compose -f /root/docker/docker-compose.yaml up -d

