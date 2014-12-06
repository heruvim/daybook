DayBook project
===============

Environment
-----------
- Java 7 JDK;
- Tomcat 7
- Maven 3.0.1+
- Mysql 5.1+

Getting Started
---------------
- Clone daybook sources;
- Configure MySQL server for using UTF-8. Change /etc/mysql/conf.d file:
  
  ```[mysqld]
  collation-server = utf8_unicode_ci
  character-set-server = utf8
  ```
  
- Configure MySQL server:
  - add new user "daybook" with password "daybook" ( values can be changed in PersistanceModule.java )
  - add new database "daybook" ( value can be changed in PersistanceModule.java )
  - grant privilegies for new database to new user

- Install NodeJS 0.1+ and npm
- Run "npm install"
- Run "grunt resolve"
- Run "mvn tomcat7:run"
