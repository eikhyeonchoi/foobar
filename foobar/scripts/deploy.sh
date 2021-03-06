#!/bin/bash
# 반드시 이 파일에도 실행권한 줄것 chmod +x deploy.sh

echo "... start of deploy.sh ..."

REPOSITORY=/home/ec2-user/jar
APPLICATION_NAME=foobar

cp /home/ec2-user/zip/build/libs/*.jar $REPOSITORY/


echo "> 현재 구동중인 어플리케이션 pid"
CURRENT_PID=$(pgrep -f ${APPLICATION_NAME})
echo "> 현재 구동중인 어플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 어플리케이션이 없습니다"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 어플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)
echo "> JAR NAME: $JAR_NAME";

chmod +x $REPOSITORY/$JAR_NAME

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-prod.properties,/home/ec2-user/prod/application-prod-add.properties \
        -Dspring.profiles.active=prod \
        $REPOSITORY/$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

echo "... end of deploy.sh ..."