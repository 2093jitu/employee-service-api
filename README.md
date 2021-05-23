# employee-service-api


#Functionlity
-------------------------------------------------------------

- Grade Setup ( The basic salary of the lowest grade will be taken as input And  The basic of the others grade         previous grade + 5000 taka)

- Employee Setup (Employee add,edit,delete)

- Employee Account Setup (Account add,edit,delete)

- Company Account Setup (Add company account if not exist and add initial balance. add new Cr.ammount and show transaction history)

- Pay Salary (Payment Employee Salary,calculate salary ,show salary status , transfer salary , show transaction history) ,show company account details)

- Print / display salary sheet

- Print / display company account

- note : Employee ID must be 4 digit and unique

# Technology
-------------------------------------------------------------------
- Spring boot, Spring JPA , Hibernate
- Rest Controller
- MySQL
- jasper Reports
- Maven

# Installation 

- git clone https://github.com/2093jitu/employee-service-api.git
- create database schema ------ 'employee'
- change spring.datasource.url from application-dev.properties
- spring.datasource.username from application-dev.properties
- spring.datasource.password from application-dev.properties
- mvn spring-boot:run
- run port http://localhost:8080

