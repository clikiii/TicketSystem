### This README is for internal use.

### **NOTE: To run this repo, please**
1. make sure [docker](https://docs.docker.com/get-docker/) is properly installed.
    ```bash
    # self test
    docker -v
    # sample output(version may not be the same)
    Docker version 20.10.14, build a224086
    ```
2. run <kbd>/Source/buildAndRun.sh</kbd>
    ```bash
    cd Source
    chmod 755 buildAndRun.sh
    ./buildAndRun.sh
    ```
3. this java project uses openjdk:19, please mind the version is correct.
4. mysql is not needed. (not tested yet)


## Create a new branch and PR when changes are made (which is for report). :D

- Please check [this](https://www.youtube.com/watch?v=MtME-ERufu0) video when you don't know how to add a dependency in vscode.

- This project uses Mysql database, please make sure mysql is installed and db properties (in ./Source/TicketSystem/src/ticketSystem/database/Database.java) is set properly.

### TO-DO List
1. use case diagram
2. class diagram (on hold currently)
3. **algorithm implementation**
4. **test cases**
5. GUI
6. fake data for Flight table
7. encapsulation the software (on hold currently)
8. report writing (on hold currently)
