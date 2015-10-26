Risk Calculator INAI contest
===

This repository contains all components of the "Risk Calculator" project for INAI contest 


Modules
=====

The project is divided into smaller specific-purpose submodules:

*	rest: spring-boot RESTful endpoints to expose risk index values.
*	webapp: front-end components to generate a cool visualization (css,js,html)

rest (building)
======

Build rest module using maven:
````
$ cd rest/
$ mvn clean package
````

rest (running)
======

Run the embedded rest service after building it by executing:
````
$ java -jar target/rest-0.1-SNAPSHOT.jar
````

Then try the endpoint using **curl** or your browser on the addres: [localhost:8080/index](http://localhost:8080/index)
