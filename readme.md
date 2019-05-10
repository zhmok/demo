# demo

[spring-boot-gradle 文档](https://docs.spring.io/spring-boot/docs/2.0.9.RELEASE/gradle-plugin/reference/html/)

## 问题
>1.fastdfs-client-java jar 不存在 <br >
由于配置的阿里maven仓库 fastdfs-client-java jar包无法下载到 

>解决办法 <br >
去github上找到 [fastdfs-client-java](https://github.com/happyfish100/fastdfs-client-java) 
然后下载后解压,进入目录运行 maven 脚本
```
mvn clean install
```