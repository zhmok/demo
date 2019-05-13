FROM java:8-alpine

# cd $(find ./ -name *.jar | xargs -i dirname {})

ADD build/libs/demo-server-1.0.0.SNAPSHOT.jar /data/app.jar

RUN set -ex

EXPOSE 8080

# 执行命令
CMD ["java","-jar","/data/app.jar"]