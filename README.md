# Goose Game Kata

Java implementation of Goose Game Kata [link](https://github.com/xpeppers/goose-game-kata)

### Prerequisites

* [Docker](https://docs.docker.com/install/)
* [JDK](https://openjdk.java.net/install/) and [maven](https://maven.apache.org)

### Run with Docker

First build the docker image with command (from project dir)
```
docker build -f docker/Dockerfile -t goose-game-kata-image .
```

Run game with

```
docker run -it --name goose-game-kata-container goose-game-kata-image
```

### Run with JDK and maven

compile with the following command (from project dir)
```
mvn clean package
```

Run game with

```
java -jar target/goose-game-1.0-SNAPSHOT.jar
```