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

rest (running locally)
======

Run the embedded rest service locally after building it by executing:
````
$ java -jar target/rest-0.1-SNAPSHOT.jar
````

Then try the endpoint using **curl** or your browser on the addres: [localhost:8080/index](http://localhost:8080/index)

rest (deploying on heroku)
======

The rest service is ready to be build and deployed using heroku. Considering that [heroku toolbelt](https://toolbelt.heroku.com/) is already installed on your system, execute the following to deploy after changes are made:

````
$ git push heroku master
````

Then try the service using the heroku generated url and appending /index at the end in your navigation bar:
````
$ heroku open
````

A new page will be loaded in your web browser, then append '/index' to the url in the navigation bar, for instance: https://frozen-plateau-1742.herokuapp.com/index