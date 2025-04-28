# Course Bundle System Spring Boot App

## Problem Domain
KK, a platform providing educational resources, wants to create a course bundle
recommendation system that returns bundle quotes based on teacher requests.

## Scenario
Teachers can request different levels of content coverage for various course topics. For
example, a teacher may want 20 resources on reading, 50 on math, and 30 on science. This
request specifies the level of content needed for each topic to create a customized bundle
for their classroom.
Based on the teacher’s request, quotes are provided by a set of resource providers. Each
provider can offer different rates for bundles depending on the topics they cover.

## Technologies
 - Java 17
- Spring Boot 3.x
- Maven
- JUnit 5
- MockMvc
- Spock Framework (optional)
- Jackson (JSON handling)
- SLF4J + Logback (logging)
- OpenAPI/Swagger (API docs)

## How to run
1. `mvn clean install`
2. `mvn spring-boot:run`

## How to test
`mvn test`

## OpenAPI
http://localhost:8080/swagger-ui/index.html

## Example request
```
POST /api/bundle
Content-Type: application/json

{
    "topics": {
        "reading": 20,
        "math": 50,
        "science": 30,
        "history": 15,
        "art": 10
    }
}
```

## Example response
```
[{
    "providerName": "provider_a",
    "bundleValue": 8.0
},
{
    "providerName": "provider_b",
    "bundleValue": 5.0
},
{
    "providerName": "provider_c",
    "bundleValue": 10.0
}]
```


## What's next?
 - Connect to a real database
 - Add authentication and authorization (Spring Security)
 - Handle dynamic provider registration
 - Dockerize the application
 - Deploy to cloud (AWS/GCP)