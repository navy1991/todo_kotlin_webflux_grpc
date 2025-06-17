<div align="center">

# todo_kotlin_webflux_grpc

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=navy1991_todo_kotlin_webflux_grpc)

[![Open in Visual Studio Code](https://img.shields.io/static/v1?logo=visualstudiocode&label=&message=Open%20in%20Visual%20Studio%20Code&labelColor=2c2c32&color=007acc&logoColor=007acc)](https://open.vscode.dev/navy1991/todo_kotlin_webflux_grpc)
![workflow](https://github.com/navy1991/todo_kotlin_webflux_grpc/actions/workflows/ci.yml/badge.svg)

Sample implementation of Spring Webflux + DDD + Clean Architecture + gRPC + Kotlin for study.


</div>

# Software Architecture

- Clean Architecture
- DDD
  - Presentation Layer
  - Infrastructure Layer
  - Application Layer
  - Domain Layer
- Non-Blocking
  - Application Server: Netty
  - Database Client: R2DBC

# Get started

- `git clone https://github.com/navy1991/todo_kotlin_webflux_grpc.git`
- `./gradlew build`
- `docker compose up -d`
- Run Application

# References

- Spring
  - https://docs.spring.io/spring-grpc/reference/getting-started.html
  - https://spring.io/guides/tutorials/spring-boot-kotlin
  - https://spring.pleiades.io/spring-boot/reference/features/kotlin.html
  - https://docs.spring.io/spring-boot/appendix/dependency-versions/coordinates.html
  - https://spring.pleiades.io/guides/gs/multi-module
- Gradle
  - https://docs.gradle.org/current/userguide/version_catalogs.html
  - https://docs.gradle.org/current/samples/sample_convention_plugins.html
- Kotlin
  - https://kotlinlang.org/docs/home.html
  - https://kotest.io/
  - https://mockk.io/
  - https://github.com/Ninja-Squad/springmockk
- gRPC
  - https://github.com/google/protobuf-gradle-plugin
  - https://protobuf.dev/reference/kotlin/kotlin-generated/
  - https://github.com/fullstorydev/grpcurl
- Database
  - https://r2dbc.io/
  - https://github.com/asyncer-io/r2dbc-mysql
  - https://docs.spring.io/spring-data/relational/reference/r2dbc.html
