========================================
========================================
Super Flight
========================================
========================================

<< Description >>
--------------------
Super flight is an application for people who wants to traval to search and book flights.
People can search direct flights and non-direct flights only by input the place of departure, destination and the date of departure.

<< Installation >>
--------------------
1. This application uses openjdk 19, please make sure your version is correct.
2. For dockerized database, please make sure docker is properly installed. 
   If not, you can install the docker via the following link：
   https://docs.docker.com/get-docker/

Windows
-----
3. Run `build.sh` in `./Source/Jar` in cmd: sh build.sh
4. Run `PreBuild.jar` in cmd: java -jar PreBuild.jar
5. Run `TicketSystem.jar` in cmd: java -jar PreBuild.jar

Mac or other Linux Environment
-----
3. Open the whole project folder in the VSCode.
4. Run `build.sh` in `./Source/Jar` in terminal: 
   (1)Run chmod 755 build.sh
   (2)Run ./build.sh
5. Run `./Source/TicketSystem/src/ticketSystem/PreStart.java` in VSCode.
6. Run `./Source/TicketSystem/src/userInterface/SearchFlight.java` in VSCode.

<< User Guideline >>
--------------------

1. You can sign up first by using, for example, username: AAA, password: 123.
2. Due to the project requirements, we can not use external API, so the available cities is limited.
   Following cities are supported:
   "Beijing", "Chongqing", "Chengdu", "Hangzhou", "Kunming", "Nanjing", "Shanghai", "Qingdao", "Wuhan", "Amoy", "Taipei", "Hong Kong".
3. You can also login by using username: admin, password: admin to login as the admin.

<< Credits >>
--------------------

Program Manager: ZHAO Yiwei
Assistant Program Manager: LI Xinagyu
Program Developer: WANG Chenchen
Program Developer: WANG Yian
Interface Developer: XIE Yifei
Program Tester: YANG Jingxian

<< End >>
