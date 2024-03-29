FROM openjdk:11-jdk as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app.jar

FROM openjdk:18-jdk

COPY --from=builder /app.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]