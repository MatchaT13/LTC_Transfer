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



INSERT INTO LTC_FACILITIES (ID, AVAILABLE_BEDS, LOCATION, NAME, PHONE, TOTAL_BEDS) VALUES
(1, 6, 'Matcha, ON', 'Matcha Grove LTC', '514-555-1001', 10),
(2, 5, 'Latte, ON', 'Latte Care Center', '450-555-2002', 8),
(3, 7, 'Tea, ON', 'Tea Senior Living', '450-555-3003', 12),
(4, 4, 'Green, ON', 'Green Years Residence', '514-555-4004', 6);

INSERT INTO BEDS (ID, BED_NUMBER, BED_TYPE, IS_OCCUPIED, FACILITY_ID) VALUES
(101, 'MA-1', 'Private', 1, 1),
(102, 'MA-2', 'Private', 0, 1),
(103, 'MA-3', 'Semi-Private', 0, 1),
(104, 'MA-4', 'Normal', 1, 1),
(105, 'MA-5', 'Normal', 0, 1),
(106, 'MA-6', 'Bariatric', 0, 1),
(107, 'MA-7', 'Specialized', 1, 1),
(108, 'MA-8', 'Respite', 0, 1),
(109, 'MA-9', 'Semi-Private', 1, 1),
(110, 'MA-10', 'Normal', 0, 1);

INSERT INTO BEDS (ID, BED_NUMBER, BED_TYPE, IS_OCCUPIED, FACILITY_ID) VALUES
(201, 'LT-1', 'Private', 0, 2),
(202, 'LT-2', 'Private', 1, 2),
(203, 'LT-3', 'Semi-Private', 0, 2),
(204, 'LT-4', 'Normal', 0, 2),
(205, 'LT-5', 'Normal', 1, 2),
(206, 'LT-6', 'Bariatric', 1, 2),
(207, 'LT-7', 'Respite', 0, 2),
(208, 'LT-8', 'Specialized', 0, 2);

INSERT INTO BEDS (ID, BED_NUMBER, BED_TYPE, IS_OCCUPIED, FACILITY_ID) VALUES
(301, 'TT-1', 'Private', 1, 3),
(302, 'TT-2', 'Private', 1, 3),
(303, 'TT-3', 'Semi-Private', 0, 3),
(304, 'TT-4', 'Semi-Private', 0, 3),
(305, 'TT-5', 'Normal', 1, 3),
(306, 'TT-6', 'Normal', 0, 3),
(307, 'TT-7', 'Normal', 0, 3),
(308, 'TT-8', 'Bariatric', 0, 3),
(309, 'TT-9', 'Specialized', 1, 3),
(310, 'TT-10', 'Respite', 0, 3),
(311, 'TT-11', 'Respite', 0, 3),
(312, 'TT-12', 'Normal', 1, 3);

INSERT INTO BEDS (ID, BED_NUMBER, BED_TYPE, IS_OCCUPIED, FACILITY_ID) VALUES
(401, 'GY-1', 'Private', 0, 4),
(402, 'GY-2', 'Semi-Private', 1, 4),
(403, 'GY-3', 'Normal', 0, 4),
(404, 'GY-4', 'Bariatric', 0, 4),
(405, 'GY-5', 'Specialized', 1, 4),
(406, 'GY-6', 'Respite', 0, 4);


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

