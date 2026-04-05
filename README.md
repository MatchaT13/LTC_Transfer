# LTC_Transfer
# LTC Transfer System
A Spring Boot web application that simulates a hospital-to-long-term-care (LTC) transfer workflow.
Users can log in as hospital staff or LTC staff to manage transfer requests and bed availability.

Tech Stack
Java 17
Spring Boot
Maven
Thymeleaf (HTML templates)
H2 Database (in-memory)

# How to Run the Application
Open the project folder (the one containing pom.xml) in VS Code or IntelliJ.
Open a terminal in that folder.
Run the following commands:

'''bash

mvn install.


mvn spring-boot:run



Once the application starts, open your browser and go to:

http://localhost:8080/login


This project uses an H2 in-memory database.

This means that:
The database is temporary and
Data is reset every time the application restarts
Demo users are automatically inserted at startup
Accessing the H2 Console


Open:

http://localhost:8080/h2-console


Use the following settings:

-Driver Class: org.h2.Driver
-JDBC URL: jdbc:h2:mem:ltcdb.


-Username: sa.


-Password: (leave blank).


Click Connect.


Once you access the h2-console it is very importsnt you insert your own data values because once the terminal is no loger running the data gets deleted,


During the presentation, we should have the data we will be running in the google slides, so that we can copy paste it into the h2-console. 




Here is an example of the data we can put in the login page but first we have to put this in the h2-console and make sure the terminal is stiil running (For reference if you go to the models folder in the VS Code , you will find i put each class and their attributes in there. The models folder defines the structure of the database):




INSERT INTO USERS (email, password, name, role, created_at)


VALUES ('hospital@test.com', '123', 'Hospital User', 'hospital', CURRENT_TIMESTAMP);


INSERT INTO USERS (email, password, name, role, created_at)


VALUES ('ltc@test.com', '123', 'LTC User', 'ltc', CURRENT_TIMESTAMP);

Important Notes: This is a prototype system for demonstration purposes



Make sure you are in the correct folder and run:

mvn clean install
mvn spring-boot:run
Login not working
Ensure the app is running
Make sure demo users are loaded (they load automatically at startup)
H2 Console not connecting

Make sure:

the app is running
the JDBC URL is exactly: jdbc:h2:mem:ltcdb
Repository


Authors: Hibaq Ahmed, Rasmandeep Dhillon, Brian Tran, Divine Consille Dikoka

(Insert team member names here)

