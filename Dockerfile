FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/virtual-classroom-system-0.0.1.jar
COPY ${JAR_FILE} app_virtualclass.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_virtualclass.jar"]