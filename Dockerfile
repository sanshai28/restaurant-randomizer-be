FROM openjdk:8-jre

RUN mkdir /app
WORKDIR /app
EXPOSE 7030

COPY target/restuarant-randomizer-be-1.0.jar /app
CMD ["java", "-Duser.timezone=GMT+08", "-jar","-Xmx2048m", "/app/restuarant-randomizer-be-1.0.jar"]