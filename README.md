DayBook project

Environment:
- Java 7 JDK;
- Tomcat 7
- Maven 3.0.1+
- Mysql 5.1+

Getting Started:
1. Clone daybook sources;
2. Configure MySQL server for using UTF-8. Change /etc/mysql/conf.d file:
[mysqld]
collation-server = utf8_unicode_ci
character-set-server = utf8
3. Configure MySQL server:
  - add new user "daybook" with password "daybook" ( values can be changed in PersistanceModule.java )
  - add new database "daybook" ( value can be changed in PersistanceModule.java )
  - grant privilegies for new database to new user
3. Run "mvn tomcat7:run" 
