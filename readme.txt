To run ElysiumFrontend locally...
* You need the following to be installed.
*** MySqlServer, Apache Tomcat 8, Apache Ant, Apache Ivy, Java 8

* Ensure that you have a MySql user with username 'root' and password 'password'.
* Ensure that you have a MySql schema named 'elysium', and that user 'root' has read/write access to this schema.

* You need to pull down the following projects from GitHub:
https://github.com/wastevens/TheWheel
https://github.com/wastevens/CommonServices
https://github.com/wastevens/Courier
https://github.com/wastevens/Elysium
https://github.com/wastevens/ElysiumFrontend

* Run the 'ant publish' target against each of these projects.  If you need to specify your ivysettings, use the ivysettings.xml in the associated project.
*** This will build the project and place a jar for depending projects in a local repository.

* Run the /ElysiumFrontend/src/resources/ElysiumFrontend-schema.sql and ElysiumFrontend-default-users.sql scripts against the elysium MySql schema.  This will create the neccesary tables and populate them with some default users.

* Run the 'ant war' target against ElysiumFrontend

* Copy /ElysiumFrontend/dist/ElysiumFrontend.war to /Tomcat/webapps/ROOT.war
*** You may want to rename any existing /Tomcat/webbapps/ROOT/ directory to preserve it's contents, as Tomcat will overwrite this directory when it explodes ROOT.war

* Start Tomcat

* You should be able to reach http://localhost:8080/login

If you ran the default users script, you can login as 'admin@ut.org' or 'patron@ut.org'; password is 'password'