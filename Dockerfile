FROM openjdk:18-jdk-alpine

<<<<<<< HEAD
ADD target/capstone-sepuluh.jar capstone-kelompok-sepuluh.jar
=======
ADD target/capstone-kelompok-sepuluh.jar capstone-kelompok-sepuluh.jar
>>>>>>> 22812d7a3b70dc4fb25e0f4998e18529111b806f

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/capstone-kelompok-sepuluh.jar"]