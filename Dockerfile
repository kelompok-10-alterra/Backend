FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/capstone-kelompok-sepuluh.jar
COPY ${JAR_FILE} capstone-kelompok-sepuluh.jar
ENTRYPOINT ["java","-jar","/capstone-kelompok-sepuluh.jar"]