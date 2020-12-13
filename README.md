# studymouse-server
Study Mouse의 Java Spring Server 저장소 입니다.
### Run Ainize

[![Run on Ainize](https://ainize.ai/static/images/run_on_ainize_button.svg)](https://ainize.web.app/redirect?git_repo=github.com/study-mouse/studymouse-server)
### API

https://studymouse-mjung1798.endpoint.ainize.ai/swagger-ui.html

### Build

```shell
# build 이전에 database & security & mail push 설정 필요.
docker build -t [docker_hub_id]/[docker_hub_repo_name]
docker push [docker_hub_id]/[docker_hub_repo_name]
```

필요한 credential 정보

```properties
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: [jdbc url]
    username: [database name]
    password: [database password]
  mail:
    host: smtp.gmail.com
    port: 587
    username: [gmail username]
    password: [gmail password]
  security:
      oauth2:
        client:
          registration:
            google:
              client-id: [googel client-id]
              client-secret: [google client-secret]
```


----------------------------------------

![image](https://user-images.githubusercontent.com/26458200/87861654-2e207800-c983-11ea-9532-9afb640d0b37.png)

## Introduce

매일같이 똑같은 영어단어를 해석하고 다시 잊고 다시 찾는 일, 지겹지않나요?
하루에 보는 수많은 단어들을 관리하는 StudyMouse로 간편하고 자연스럽게 단어를 습득하는 환경을 만들어 보세요.

### ScreenShot

![image](https://user-images.githubusercontent.com/34976178/87865194-47d6b500-c9ad-11ea-8c44-580ab13f6c82.png)

![image](https://user-images.githubusercontent.com/34976178/87865203-6472ed00-c9ad-11ea-9cd7-4ffa333a0b56.png)


## Server Architecture

![image](https://user-images.githubusercontent.com/34976178/87866475-22ea3e00-c9bd-11ea-88c7-c23854ca6d5f.png)

### Develop Environment

- Language : Java
- IDE : Intellij IDE
- BuildTool : Gradle 6.4
- Spring Boot 2.0
- Spring Data JPA
- Google Cloud Platform VM
- Maria DB

### Dependency
- spring-starter-security
- spring-starter-mail
- swagger-ui, swagger2
- queryDsl
- lombok

### contributor

- 김민정([github link](https://github.com/mjung1798))
- 이민형([github link](https://github.com/gobukgol))
