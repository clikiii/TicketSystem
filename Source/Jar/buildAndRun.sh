# !/usr/bin/env bash

create_time=`docker inspect -f '{{ .Created }}' 3343_5_mysql`
if [[ -z "${create_time}" ]]
then
    echo "Container does not exist. Creating a new one..."
    docker build -t cs3343_group5/ticket_system .
    docker run -d -p 3311:3306 --name 3343_5_mysql -e MYSQL_ROOT_PASSWORD=123456 cs3343_group5/ticket_system
fi

if [[ -n "${create_time}" ]]
then
    create_day="${create_time%T*}"
    read -p "Last created on $create_day, do you want to update the data in database?: [yes] " res
    res=${res:-yes}
    if [[ $res =~ yes ]]
    then
        echo "Updating..."
        docker rm --force /3343_5_mysql
        docker build -t cs3343_group5/ticket_system .
        docker run -d -p 3311:3306 --name 3343_5_mysql -e MYSQL_ROOT_PASSWORD=123456 cs3343_group5/ticket_system
    fi
fi

echo
echo "This project uses openjdk:19, if you cannot run the jar files, please make sure correct version is used."
/usr/bin/env /usr/local/Cellar/openjdk/19/libexec/openjdk.jdk/Contents/Home/bin/java --enable-preview -jar PreStart.jar
/usr/bin/env /usr/local/Cellar/openjdk/19/libexec/openjdk.jdk/Contents/Home/bin/java --enable-preview -jar TicketSystem.jar
