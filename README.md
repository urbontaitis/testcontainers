# Testcontainers demo

[Video rental system](https://github.com/jakubnabrdalik/hentai/) example of using testcontainers:

* [Testcontainers](https://www.testcontainers.org/)
* [Playtika / testcontainers-spring-boot](https://github.com/Playtika/testcontainers-spring-boot)

## Prerequisites
* Docker
* Java 17

## Running application

Start MySQL database instance:
`docker compose up -d`

Start the application:
`./gradlew bootRun`

## Executing tests

`./gradlew clean test`


### Running MySQL arm docker image

Create `docker-compose.override.yml` to use arm68v8 based docker image:
```yml
services:
  db:
    image: arm64v8/mysql:oracle
```


### Run slides locally
You need to have [marp.app](https://marp.app)

`marp slides.md -w -o docs/index.html`

