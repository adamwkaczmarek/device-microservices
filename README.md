# DEVICE MICROSERIVCES


## PROJECT DESCRIPTION:

This project allow registration and managing data sended from microcontrollers devices like for instance ESP8266 NODEmcu V3.
In principle, the project should be working on the AWS platform. (it using following AWS serivces  SQS, SNS and IoT ).

# Building the Docker Images
To build the project as a docker image, open a command-line window change to the directory where you have downloaded the source code.

Run the following maven command.  This command will execute the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin) defined in the pom.xml file.  

   **mvn clean package docker:build**

If everything builds successfully you should see a message indicating that the build was successful.


# Running the services 

Now we are going to use docker-compose to start the actual image.  To start the docker image, go  to the directory 

**project dir/docker/common** 

and then run folowing command :

**docker-compose up**

If everything starts correctly you should see a bunch of Spring Boot information fly by on standard out.  At this point all of the services available in project will be running.
