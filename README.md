# ESD-Project
Project work for Enterprise Software Development.

Task: To create a salary disbursement system which allows the employees to disburse the salary of a single faculty or a selection of faculties.

We have used JWT(JSON Web Token) Tokens for the purpose of authentication and logging in to the system. We have used springboot for the backend of our project and react for the frontend of our project. 

# 1. Project Goals
Our aim was to create an interactive easy-to-use interface for employees and administrators. We had to ensure a secure way of performing transactions of salaries by only authenticated users.

# 2. Architecture Overview

## Backend -
We used Springboot to build the backend of our project. Springboot allows us to create applications using Java language while providing us with bundle of libraries that make our project startup and management easier. The given project was implemented in a layered architecture consiting of mainly four layers-
1. Controller Layer
2. Service Layer
3. Repository Layer
4. Model/Entity Layer

The Controller Layer acts as an entry point for the application where it receives request from the user an delegates the task to the service layer. It handles request the incoming requests (eg. GET, POST, PUT, DELETE) and returns the appropriate HTTP response back tot the client. Our Project consists of two Controller Layers, the **AuthController** layer and the **EmployeeController** Layer. The **AuthController** is responsible for validating the user while the **EmployeeController** layer is responsible for handling the requests of the employees.  

The Service Layer is used to implement the business logic of the application. It interacts with the repository layer to persist or retreive the data. We have only one service layer **EmployeeService** layer.  

The Repository Layer is responsible for directly interacting with the database. It contains the method of of querying and modifying the data. It is responsible for performing CRUD operations such as (Create, Read, Update, Delete). We managae presistence and retrieval of data throught ORM frameworks like JPA(Java Persistence API). There are three repository layers in our given project **DepartmentsRepo**, **EmployeeRepo** and **EmployeeSalaryRepo**.  

The Model/Entity Layer is represents the database/data structures and domain objects of the application. We defind the database we are going to work with in the form of a class and define the field as entities of the class. It represents data as a form of object. The entities for our project are **Departments**, **EmployeeAccounts**, **Employees** and **EmployeeSalary**.  

## Frontend -  

# 3. Authentication and LogIn -  
We implement authentication in our Project with the help of JWT tokens. JWT tokens are used for secure authentication and authorization. JWT is a compace, URL safe token that can securely transmit and information between parties as a JSON object. These tokens are verified and are used to grant access to protected resources. The user enters their credentials and sends a POST request to the backend of our API. The backend returns a JWT token on successful authentication which is stored securely in the frontend of our Project. If the tokens expire, the frontend redirects the user to the login page or provides a refresh mechanism. 
