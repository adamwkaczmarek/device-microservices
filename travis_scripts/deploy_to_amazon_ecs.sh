echo "Launching $BUILD_NAME IN AMAZON ECS"
ecs-cli configure profile --access-key $AWS_ACCESS_KEY --secret-key $AWS_SECRET_KEY
ecs-cli configure --cluster devices-ms  --region eu-central-1
ecs-cli compose --file docker/common/docker-compose.yml up
rm -rf ~/.ecs
