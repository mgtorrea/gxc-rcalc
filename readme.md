Risk Calculator INAI contest
===

This repository contains all components of the "Risk Calculator" project for INAI contest 


Modules
=====

The project is divided into smaller specific-purpose submodules:

*	rest: spring-boot-web application to expose the keywords consumption through a RESTful service
*	webapp: front-end components to generate a cool visualization (css,js,html)


rest
=====

building
======

Build rest module using maven:
````
cd rest/
mvn clean package
````

running
======

Run the embedded rest service after building it by executing:
````
java -jar target/rest-0.1-SNAPSHOT.jar
````
