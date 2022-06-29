---
title: Testcontainers
description: 
marp: false
html: true
theme: gaia
url: https://www.urbontaitis.lt/testcontainers

---
<style>
:root {
    color: #fff;
    font-family: 'MonoLisa Regular', serif !important;    
    background-blend-mode: multiply;        
    /* background: #00385b url("https://raw.githubusercontent.com/urbontaitis/renovatebot/main/images/background.png") no-repeat center center;  */
    background: #00385b url("media/AE1A6464.jpg") no-repeat center center; 
    background-color: rgba(0, 0, 0, 0.7);    
    background-size: cover;
}

h1 {
    text-align: left;    
}

</style>

<style scoped>
section {        
    background-blend-mode: multiply;        
    background: #00385b url("media/AE1A6464.jpg") no-repeat center center; 
    background-color: rgba(0, 0, 0, 0.5);    
    background-size: cover;
}

h4 {
    background: url("media/metasite-logo.png") no-repeat right top; 
    width: 100%;
    padding-bottom: 250px
}

h6 {              
    color: #8bbee8;
    font-size: medium; 
}

footer{
    color: #fff;
    font-size: small;
}
</style>

####

# Testcontainers

##

###### Mindaugas Urbontaitis

###### 2022-06-30

<!-- _footer: "www.metasite.net" -->

---

![bg left:30% w:90% vertical](https://raw.githubusercontent.com/urbontaitis/renovatebot/main/images/me.jpg)
![bg left:30% w:90% vertical](https://raw.githubusercontent.com/urbontaitis/renovatebot/main/images/pbp-2019.png)
![bg left:30% w:90% vertical](https://raw.githubusercontent.com/urbontaitis/renovatebot/main/images/ig.png)

# Hello 👋

- Java developer
    - Team 8

- Outdoor activities
  - My biggest 🚴‍♂️ achievement [Paris - Brest - Paris 2019](https://www.strava.com/activities/2674219873)
  - My 📸 [365 days](https://www.instagram.com/m.urbo/) challenge

---

# The problem

Video rental system which needs to support multiple databases
 - MySQL
 - MongoDB


<!-- _footer: "Source [Video rental system example](https://github.com/jakubnabrdalik/hentai)" -->

---

# The project specification

```groovy

def "should get film details"() {
    given: "module has film Rambo"    
    when: "client asks for details of Rambo"    
    then: "module returns details of Rambo"    
  }

  def "when no film found"() {
    when: "client asks for details of Rambo"    
    then: "module does not return rambo"    
  }

  def "should get films"() {
    given: "module has two films"    
    when: "client asks for films"    
    then: "module returns both films"    
  }

```
---

# Unit testing

- Fast, but no:
  - Database schema check
  - Check if code works as expected when runs in the production environment 
  - Multiple database verification

<!-- Show "fake" repository code example -->

---

# Integration tests

### Flyway + H2 = ✅ ?

- Flyway - for mysql database versioning
- H2 in memory database 

<!-- _footer: "
[Flyway](https://flywaydb.org) | 
[H2 database engine](http://h2database.com/)" 
-->
---

# H2 database demo #1 

```gradle
dependencies {
  implementation 'org.flywaydb:flyway-core'
  implementation 'org.flywaydb:flyway-mysql'

  testImplementation 'com.h2database:h2'
}
```

---

# Production DB

- Expensive
- High chance of failing tests 
    - when tests are executed by
        - CI
        - developers

![bg right:45% w: 90%](media/production.jpg)


<!-- _footer: "Source [Image](https://imgflip.com/memegenerator)" -->

---

# What it is Testcontainers?

![bg](media/tc-logo.png)

<!-- 
Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container. 
-->


<!-- _footer: "Source [Testcontainers](https://www.testcontainers.org/)" -->
---

# Testcontainers

- Data access layer integration tests (e.g. @DataJpaTest)
- Application integration tests
- UI/Acceptance tests
- Allows to create your own custom container classes

<!-- _footer: "Source [Testcontainers](https://www.testcontainers.org/)" -->

---

# MySQL database demo #2 

```gradle
dependencyManagement {
    imports {
        mavenBom "org.testcontainers:testcontainers-bom:1.17.3"
    }
}

dependencies {
    testImplementation 'org.testcontainers:spock'
    testImplementation 'org.testcontainers:mysql'
}
```

<!-- Testcontainers/ryuk - runs automatically containers cleanup -->

---

# MySQL database demo #3 

Embedded MySQL: 

```gradle
dependencies {
  testImplementation "org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.3"
  testImplementation "com.playtika.testcontainers:embedded-mysql:2.2.0"
}
```

Configuration:
https://github.com/Playtika/testcontainers-spring-boot/tree/develop/embedded-mysql

---

# MongoDB database demo #4 

Embedded MongoDB: 

```gradle
dependencies {
    testImplementation "org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.3"
    testImplementation "com.playtika.testcontainers:embedded-mongodb:2.2.0"
}

```

Configuration:
https://github.com/Playtika/testcontainers-spring-boot/tree/develop/embedded-mongodb

---

# Flyway demo #5

![bg 40% center](media/data_migration.jpg)


<!-- _footer: "Source [image](http://www.quickmeme.com/p/3vmoy5)" -->
<!-- RELEASE_DATE DATE >
<!-- V1.1__release_date.sql -->
<!--- ALTER TABLE film ADD COLUMN RELEASE_DATE DATE DEFAULT NULL; -->
---

# Conclusion

"Production" environment

Testcontainers:   
 ``` 
    - Easy to setup
    - Configure test container image via connection URL   
    - host-less URI (///) 
    - Requires writing custom image substitutor or image name prefixed to be able to use Arm images
```

Playtika testcontainers wrapper:    
```
    - Docker image defined via bootstrap.properties
    - Provides created container connection details like host URI, username and password
    - Requires Spring Cloud Test dependency
```

---

# Thanks! 🙏

- Resources:
    - [Testcontainers](https://www.testcontainers.org/)
    - [Playtika / testcontainers-spring-boot](https://github.com/Playtika/testcontainers-spring-boot)
        - [embedded-mysql](https://github.com/Playtika/testcontainers-spring-boot/tree/develop/embedded-mysql)
        - [embedded-mongodb](https://github.com/Playtika/testcontainers-spring-boot/tree/develop/embedded-mongodb)
