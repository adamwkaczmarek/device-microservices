#!/bin/sh

getPort() {
    echo $1 | cut -d : -f 3 | xargs basename
}

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! `nc -z eureka-service $EUREKASERVER_PORT`; do sleep 3; done
echo "******* Eureka Server has started"


echo "********************************************************"
echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
echo "********************************************************"
while ! `nc -z config-service $CONFIGSERVER_PORT`; do sleep 3; done
echo "*******  Configuration Server has started"


echo "********************************************************"
echo "Waiting for the gateway to start on port $ZULLSERVER_PORT"
echo "********************************************************"
while ! `nc -z zuul-service $ZULLSERVER_PORT`; do sleep 3; done
echo "*******  Zuul Server has started"

echo "********************************************************"
echo "Waiting for the device registration service to start on port $DEVICE_REG_PORT"
echo "********************************************************"
while ! `nc -z device-registration-service $DEVICE_REG_PORT`; do sleep 3; done
echo "*******  Device registration Server has started"

echo "********************************************************"
echo "Starting device-controller-service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI             \
     -Dspring.cloud.config.uri=$CONFIGSERVER_URI                          \
     -Dspring.profiles.active=$PROFILE                                   \
     -Dsecurity.oauth2.resource.userInfoUri=$AUTHSERVER_URI               \
     -jar /usr/local/device-controller-service/@project.build.finalName@.jar
