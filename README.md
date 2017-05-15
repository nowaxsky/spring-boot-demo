# Spring Boot Demo
This project is an implementation the employee data by using Spring Boot, Hibernate and Spring Data JPA.<br/>
Built-in database Apache Derby is used to store the data.

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
    "name": "Chuck",
    "number": "9487",
    "gender": "male",
    "cellphone": "0987487987",
    "address": "Taipei",
    "age": 36
  }

#### Update employee (PUT)
http://localhost:8080/{No.}
<br/>
e.g.  
  {
    "name": "Chuck",
    "number": "9487",
    "gender": "male",
    "cellphone": "0987987987",
    "address": "Ilan",
    "age": 36
  }

#### Delete employee (DELETE)
http://localhost:8080/{No.}

#### Search an employee (GET)
The default value of each term for searching is empty("").
<br/>
This method will return the brief table with column "name", "number" and "gender". 
<br/>
http://localhost:8080/search/?name={Name}&number={No.}
<br/><br/>
e.g.<br/>
http://localhost:8080/search/?name=Chuck<br/>
http://localhost:8080/search/?number=9487<br/>
http://localhost:8080/search/?name=Chuck&number=9487


