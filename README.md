# Resources Microservice

Microservice consists of two submodules:
1. Domain - contains simple domain objects
2. App - contains logic

## First run

In order to compile the app the first time, you'll need to run the flyway migrations and jOOQ code-generation. This 
requires running a Postgres docker container with username `postgres`, password `password`, and a database of 
`resources`. This can be done with the following command:

```
docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=resources -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```

Once the container is running, you can run `./mvnw install` to build and publish the domain module to the local 
repository, apply the flyway migration, and run the code generation.

This process is required whenever database migrations are added.

Running `./mvnw spring-boot:run -pl app` will start the application.
