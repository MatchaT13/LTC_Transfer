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
mvn install.
mvn spring-boot:run.
Once the application starts, open your browser and go to:

http://localhost:8080/login


This project uses an H2 in-memory database.

This means that:
The database is temporary
Data is reset every time the application restarts
Demo users are automatically inserted at startup
Accessing the H2 Console

Open:

http://localhost:8080/h2-console

Use the following settings:

-Driver Class: org.h2.Driver
-JDBC URL: jdbc:h2:mem:ltcdb
-Username: sa
-Password: (leave blank)

Click Connect.



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

