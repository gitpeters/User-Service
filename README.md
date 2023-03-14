# User-Service

## About the Application:

It is a simply `SpringBoot` application that performs basic CRUD
operation that sends notification to user on successfully creation using [JMS](https://spring.io/guides/gs/messaging-jms/) as the message broker and [mailtrap](https://mailtrap.io/) as the message domain.

## Dependencies:
The followings are the dependencies used in this application:

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
## Tools
- IntelliJ IDE
- MySQL
- Github

## Technology
- SpringBoot