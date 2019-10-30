# Goose Game Kata

Java implementation of Goose Game Kata [link](https://github.com/xpeppers/goose-game-kata)

### Prerequisites

* [Docker](https://docs.docker.com/install/)

### Running

First build the docker image with command (from project root)
```
docker build -f docker/Dockerfile -t goose-game-kata-image .
```

Run with

```
docker run --name goose-game-kata-container goose-game-kata-image
```
