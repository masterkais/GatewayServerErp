FROM  openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY target/gateway-server-erp-0.0.1-SNAPSHOT.jar gateway-server-erp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/gateway-server-erp-0.0.1-SNAPSHOT.jar"]