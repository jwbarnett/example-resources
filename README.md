# Resources Microservice

Microservice consists of two submodules:
1. Domain - contains simple domain objects
2. App - contains logic

In order to compile, you'll need to run the flyway migrations and jOOQ
code-generation. This involves:

1. Running a Postgres docker container with username `postgres`, password
`password`, and a database of `resources`
2. Running `./mvnw install` to build and publish the domain module to the local repository,
apply the flyway migration, and run the code generation
3. Running `./mvnw spring-boot:run -pl app` will start the application

Once that's been done you should be able to generate entities and query them
in the database
