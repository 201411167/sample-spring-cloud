# Sample Spring Cloud

## [ API ]

| PORT | URL               | METHOD | 기능                                      |
| ---- | ----------------- | ------ | ----------------------------------------- |
| 9999 | /api/user/all     | GET    | DB에 저장된 모든 User 조회                |
| 9999 | /api/user/{email} | GET    | email에 해당하는 키 값을 가지는 User 조회 |
| 9999 | /api/user/save    | POST   | 새로운 User를 DB에 저장                   |
| 9999 | /api/user/update  | PUT    | User의 name 정보 갱신                     |
| 9999 | /api/user/delete  | DELETE | User를 DB에서 삭제                        |

## [ Dependency ]

```xml
<!-- Eureka Server -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

```xml
<!-- Eureka Client -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

```xml
<!-- Zuul -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>2.2.5.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
```

## [ application.yml ]

```yaml
# Eureka Server
server:
  port: 8761
eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/
    register-with-eureka: false
    fetch-registry: false
```

```yaml
# Eureka Client
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/eureka-user
  application:
    name: eureka-client-user
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
    register-with-eureka: true
    fetch-registry: true
server:
  port: 8081
management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    shutdown:
      enabled: true
```

```yaml
# Zuul
server:
  port: 9999
spring:
  application:
    name: zuul
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
    register-with-eureka: true
    fetch-registry: true
zuul:
  routes:
    eureka-client-user:
      path: /api/user/**
      service-id: eureka-client-user
```



