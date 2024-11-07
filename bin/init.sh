#!/usr/bin/env bash
PROJECT_NAME=$1

if [[ -z "$PROJECT_NAME" ]]; then echo "Project name not specified"; exit 1; fi

sed -i "s/TEMPLATE_PROJECT/${PROJECT_NAME}/" TEMPLATE_PROJECT-server/src/main/java/io/myplant/template/config/SwaggerDocConfig.java
sed -i "s/TEMPLATE_PROJECT/${PROJECT_NAME}/" TEMPLATE_PROJECT-server/src/main/resources/application.yml
sed -i "s/TEMPLATE_PROJECT/${PROJECT_NAME}/" TEMPLATE_PROJECT-server/build.gradle
sed -i "s/TEMPLATE_PROJECT/${PROJECT_NAME}/" settings.gradle
sed -i "s/TEMPLATE_PROJECT/${PROJECT_NAME}/" .github/workflow-config.env

mv TEMPLATE_PROJECT-api/ ${PROJECT_NAME}-api
mv TEMPLATE_PROJECT-server/ ${PROJECT_NAME}-server

./gradlew clean build
git add ${PROJECT_NAME}-*
