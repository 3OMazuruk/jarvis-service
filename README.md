## **Back-end service for Task-Manager**

**_INSTALLATION_**

To run this application you need to have your pc installed GitBash.
For example, this service will be deployed on Heroku and used free remote Mysql.

Step by step installation:
1) Clone this project on your PC:   
``$ git clone https://github.com/Mazuruk-O/jarvis-service.git``
2) Sign up at [Remote Mysql]:   
    2.1) Create new database    
    2.2) Copy username, password and database_name and set this value in application.yaml
3) Need to install [Heroku CLI] on your PC
4) Sign up at [Heroku] 
5) Run command line and navigate to the project folder. Run this command:   
5.1) ``$ heroku login``     
5.2) ``$ heroku create``     
5.3) ``$ git push heroku master``   
5.4) ``$ heroku logs --tail``       

#### _**About**_

The project is built on architecture REST using Spring Boot, Spring Cloud and Spring Security. 
You can register/authorize users and perform CRUD task assignment.

_**Important endpoints**_   
``/oauth/token`` - authorize user   
``/users/create`` - registry user   
``/oauth/check/token`` - check access token     
``/users/current`` - info about current user 

[Heroku CLI]: https://devcenter.heroku.com/articles/heroku-cli
[Heroku]: https://heroku.com/
[Remote Mysql]: https://remotemysql.com/