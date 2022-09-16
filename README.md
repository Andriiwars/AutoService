## Description
This is a simple web application that simulates the work of car service.
this application allows you to add the driver and his car, a description of the problem, 
which mechanic should fix the problem, and how much to pay to correct the problem.
There is a calculation of the final cost of the order with discounts 
and how much you need to pay as a salary to the mechanic.

## Requests

- POST: /owners 
- POST: /cars
- POST: /repairmans
- POST: /orders
- POST: /orders/{id}/goods
- POST: /orders/{id}/favors
- GET: /orders/{id}/price?bonus={bonus}
- GET: /repairmans/{id}/orders
- GET: /repairmans/{id}/orders/{id}/salary
- PUT: /orders/{id}}/status?newStatus="STATUS"
- PUT: /cars/{id}
- PUT: /favors/{id}
- PUT: /goods/{id}
- PUT: /orders/{id}
- PUT: /owners/{id}
- PUT: /repairmans/{id}


## Technologies
- Java version 11
- Spring
- PostgresSQL
- Maven
- Swagger

## How to run project
- Install pgAdmin and PostgresSQL
- Install and configure and create a schema in pgAdmin
- Clone this project
- Configure application.properties file from main.resources directory to create connection to db:
```java
        spring.datasource.username="your username"
        spring.datasource.password="your password"
