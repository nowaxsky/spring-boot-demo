# Spring Boot Demo
This project is an implementation the employee data by using Spring Boot, Hibernate and Spring Data JPA.<br/>
You have to run the script in MySQL first. (/src/main/resources/demodb-start.sql)

## Scenario
1. Add employee data to the database
2. Update employee data (param is No.)
3. Delete employee data (param is No.)
4. Query an employee (param are No. or name)
5. Show all employees

## Employee data
1. Name (String)
2. Employee No. (String) PRIMARY KEY
3. Gender (String)
4. Cellphone (String)
5. Address (String)
6. Age (int)
7. Created time (Time)
8. Last updated time (Time)

## How to use
#### Show all employees (GET)
http://localhost:8080/

#### Add employee (POST)
http://localhost:8080/
<br/>
e.g. 
  {
    "name": "Susan",
    "id": "9502",
    "gender": "female",
    "cellphone": "0955355655",
    "address": "Taipei",
    "age": 16
  }

#### Update employee (PUT)
http://localhost:8080/{No.}
<br/>
e.g.  
  {
    "name": "Chuck",
    "id": "9487",
    "gender": "male",
    "cellphone": "0987987987",
    "address": "Ilan",
    "age": 18
  }

#### Delete employee (DELETE)
http://localhost:8080/{No.}

#### Search an employee (GET)
The default value of each term for searching is empty("").<br/>
You can search employees in database by using all features and use pageix and pagesize to show the result as you want.
<br/>
This method will return the brief table with column "name", "id" and "gender". 
<br/>
http://localhost:8080/search/?name={Name}&id={No.}&gender={Gender}&cellphone={Cellphone}&address={Address}&age={number}&pageix={Pageix}&pagesize={Size}
<br/><br/>
e.g.<br/>
http://localhost:8080/search/?address=Taipei<br/>
http://localhost:8080/search/?id=9487<br/>
http://localhost:8080/search/?gender=male&address=Taipei&pageix=1&pagesize=3


