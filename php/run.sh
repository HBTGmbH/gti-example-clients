#!/bin/sh

docker-compose down || true
docker volume rm -f php_build || true

docker volume create php_build
docker run -d -v php_build:/data --name copyjob --rm ubuntu tail -f /dev/null
docker cp ./src copyjob:/
docker exec -it copyjob sh -c 'cp /src/* /data/'
sleep 1
docker exec -it copyjob ls /data
docker rm -f copyjob

docker-compose up composer
docker-compose up nginx php

docker-compose down || true
docker volume rm -f php_build
