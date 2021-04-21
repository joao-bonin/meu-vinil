$/bin/bash

# Provisiona os containers Dockers para execução da Aplicação
docker-compose --compatibility \
  -f infra/mysql-compose.yml \
  -f infra/activemq-compose.yml \
  up
