FROM openjdk:18-jdk-alpine

ADD target/capstone-kelompok-sepuluh.jar capstone-kelompok-sepuluh.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/capstone-kelompok-sepuluh.jar"]