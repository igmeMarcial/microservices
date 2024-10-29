#Utilizar imagen base de OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

#Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

#Copia el archivo JAR del microservicio a la imagen
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

#Exponer el puerto 8080 para el contenedor
EXPOSE 8080

#Comando para ejecutar la aplicacion
ENTRYPOINT [ "java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar" ]