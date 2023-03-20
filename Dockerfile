FROM maven:3.9.0-eclipse-temurin-17-alpine as step1
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

FROM eclipse-temurin:17-jdk-alpine
COPY --from=step1 /home/app/target/ontop-1.0-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]