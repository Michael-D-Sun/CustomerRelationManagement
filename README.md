# What is it?

This is a java web project.
The project layers are  like below.

UserService:
    This layer implement all the business logic.

User:
    This is the data structure.

UserDao:
    This layer query/write the data from/to database.
    This version we just simply use the xml as the database.

Servlet:
    This layer deal with the request from the browser.

BrowserPage:
    register.jsp
    login.jsp
    welcome.jsp
