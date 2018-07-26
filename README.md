## Spring Boot Reactive Web Example with Mongo Reactive and Akka

### _From Reactive Spring Boot_ 
_By Josh Long_

This example was taken from [Lesson 4: Reactive Streams Redux](https://www.safaribooksonline.com/videos/reactive-spring-boot/9780135255124/9780135255124-RSBL_01_04_00) of this course.

#### Requirements
- Java 10
- Maven
- MongoDB

#### Running

```jshelllanguage
.../reactive-web-mongo-akka$ mvn spring-boot:run
```

#### Testing
Getting All Tweets
```http request
curl -X GET http://localhost:8080/tweets
```
Getting All Hashtags
```http request
curl -X GET http://localhost:8080/hashtags
```
