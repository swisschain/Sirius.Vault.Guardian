#!/bin/bash
#cloning repository
if ! [ -d /root/Sirius.Vault.Guardian ];then
if [ ! -n "$(grep "^github.com " ~/.ssh/known_hosts)" ]; then ssh-keyscan github.com >> ~/.ssh/known_hosts 2>/dev/null; fi;
ssh-agent bash -c "ssh-add /root/.ssh//id_rsa; git clone git@github.com:swisschain/Sirius.Vault.Guardian.git /root/Sirius.Vault.Guardian";
else
cd /root/Sirius.Vault.Guardian && ssh-agent bash -c "ssh-add /root/.ssh/id_rsa; git pull";
fi

#building docker
tag_repo=`cd /root/Sirius.Vault.Guardian && git describe --tag`
tag_docker=`echo $tag_repo |awk -F- '{print $2}'`
cd /root/Sirius.Vault.Guardian && docker build -t swisschains/sirius-vault-guardian:s390x-$tag_docker -f docker/Dockerfile .
echo "$DOCKER_PASSWORD" | docker login --username $DOCKER_USERNAME --password-stdin
docker push swisschains/sirius-vault-guardian:s390x-$tag_docker
