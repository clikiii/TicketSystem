# !/usr/bin/env bash
# remove the old docker if exist
docker rm --force /3343_5_mysql

docker build -t cs3343_group5/ticket_system .

docker run -d -p 3311:3306 --name 3343_5_mysql -e MYSQL_ROOT_PASSWORD=123456 cs3343_group5/ticket_system

# this project uses openjdk:19
java --enable-preview -jar PreStart.jar
java --enable-preview -jar TicketSystem.jar
