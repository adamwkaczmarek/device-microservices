echo "Pushing service docker images to docker hub ...."
docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker push $DOCKER_USERNAME/device-data-service:$BUILD_NAME
docker push $DOCKER_USERNAME/device-registration-service:$BUILD_NAME
docker push $DOCKER_USERNAME/device-state-service:$BUILD_NAME
docker push $DOCKER_USERNAME/device-controller-service:$BUILD_NAME
