FROM maven:3.6-jdk-8 as build
COPY . /home/goose-game
WORKDIR /home/goose-game
RUN mvn clean package

FROM openjdk:8-alpine
COPY --from=build /home/goose-game/target/goose-game-1.0-SNAPSHOT.jar /home/app/goose-game-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/home/goose-game/goose-game-1.0-SNAPSHOT.jar"]
