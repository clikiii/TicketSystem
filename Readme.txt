========================================
========================================
Super Flight
========================================
========================================

<< Description >>
--------------------
Super flight is an application for people who want to travel to search and book flights.
People can search direct flights and non-direct flights only by inputting the place of departure, destination and date of departure.

<< Installation >>
--------------------
We recommend running the GUI under **MacOS**.

1. This application uses openjdk 19, please make sure your version is correct.
2. For the dockerized database, please make sure the docker is properly installed. 
   If not, you can install the docker via the following link：
   https://docs.docker.com/get-docker/
3. Open the root project folder (that is, the folder contains the .vscode folder) in the VSCode.

Windows
-----
4. Run `build.sh` in `./Source/Run` in cmd: sh build.sh

Mac or other Linux Environment
-----
4. Run `build.sh` in `./Source/Run` in the terminal: 
   (1)Run chmod 755 build.sh
   (2)Run ./build.sh

5. Run `./Source/TicketSystem/src/ticketSystem/PreStart.java` in VSCode, and wait for 1 minute.
6. Run `./Source/TicketSystem/src/userInterface/SearchFlight.java` in VSCode.

<< User Guideline >>
--------------------

1. You can sign up first by using, for example, username: AAA, password: 123.
2. Due to the project requirements, we can not use external API, so the available cities are limited.
   The following cities are supported:
   "Beijing", "Chongqing", "Chengdu", "Hangzhou", "Kunming", "Nanjing", "Shanghai", "Qingdao", "Wuhan", "Amoy", "Taipei", "Hong Kong".
3. You can also login by using username: admin, password: admin to login as the admin.

<< Credits >>
--------------------

Program Manager: ZHAO Yiwei
Assistant Program Manager: LI Xiangyu
Program Developer: WANG Chenchen
Program Developer: WANG Yian
Interface Developer: XIE Yifei
Program Tester: YANG Jingxian

<< End >>
