Risk Calculator INAI contest
===

This repository contains all components of the "Risk Calculator" project for INAI contest.
Check the app functionality [on video](https://www.youtube.com/watch?v=MBlYHWENgHA&feature=youtu.be)


Modules
=====

The project is divided into smaller specific-purpose submodules:

*	rest: spring-boot RESTful endpoints to expose risk index values.
*	webapp: front-end components to generate a cool visualization (css,js,html)
*	crawlers: apache flume plugin with custom crawlers and configuration to start flume agents using them to collect data

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


crawlers
======

This submodule contains custom components (web crawlers) encapsulated as a flume plugin used to collect data from sites regarding companies. This data is then used to calculate a riskIndex for each company.
To build the plugin and start crawling data, [apache flume](https://flume.apache.org/download.html) must be installed and the following env variables correctly set:
````
FLUME_HOME=/path/to/flume/
PATH=$PATH:$FLUME_HOME/bin
````

Then build the plugin and install it using the provided script:
````
$ sh build-plugin.sh
````

Now you can start crawling data by starting the configured agents. Note that configuration files are located inside **crawlers/conf** directory:
````
$ sh start-agent.sh
````

Mobile app
======

To build the mobile app you must use cordova. To install cordova you must follow the instructions shown in https://cordova.apache.org/#getstarted.  Follow https://cordova.apache.org/docs/en/latest/guide/platforms/ to setup the required environment to develop, build and test the application on a device.

Use **cordova run android** command to deploy to a connected device. You must be located on **webapp/** directory.
