# icnp-web-user 프로젝트
SpringBoot 프레임워크 기반 Hexagonal 아키텍처 적용 Skeleton 코드

### 디펜던시 버전
SpringBoot : 3.0.2  
Java : 17  
Swagger : springdoc-openapi v2.0.2  
PostgreSQL : 15.2  
Gradle : 7.6

### 프로젝트 구조
- adaptor  
  - in : inbound 거래  
  - out : outbound 거래 (인터페이스 연계)  
    - feignclient : 연계 인터페이스  
    - persistence : DB 저장 인터페이스  
      - entity : DB 저장 객체 정의  
- biz  
  - user : 비지니스 로직  
- domain  
  - user : 인터페이스 전달용 객체정의  
### Component  
Resilience4J : circuit breaker  
FeignClient : MSA간 HTTP 통신 인터페이스  
FeginErrorDecoder : FeignClient Exception 처리

### 회원 API  
index 화면 : GET http://api.icnp.in-soft.co.kr/users/index/screen  
회원 가입 화면 :  GET http://api.icnp.in-soft.co.kr/users/save/screen  
회원 로그인 화면 : GET http://api.icnp.in-soft.co.kr/users/login/screen   
회원 리스트 화면 : GET http://api.icnp.in-soft.co.kr/users/list/screen  

회원 가입 : POST http://api.icnp.in-soft.co.kr/api/v1/users  
회원 로그인  : GET http://api.icnp.in-soft.co.kr/api/v1/users/{userEmail}/{userPassword}  
회원 리스트 : GET http://api.icnp.in-soft.co.kr/api/v1/users  
회원 삭제 : DELETE http://api.icnp.in-soft.co.kr/api/v1/users  

#### 상품 연계거래 화면
회원별 상품조회 : GET http://api.icnp.in-soft.co.kr/users/products/{userId}/screen  

### SWAGGER  
SWAGGER UI : http://api.icnp.in-soft.co.kr/users/swagger-ui/index.html  
API DOCS : http://api.icnp.in-soft.co.kr/users/v3/api-docs  
YAML 파일 : http://api.icnp.in-soft.co.kr/users/v3/api-docs.yaml  

### 설명  
#### DB R/W 분리  
1. 환경설정  
```
annotationProcessor("org.springframework.boot:spring-boot-configuration-processor") // DB RW 분리
implementation("icnp:icnp-common-lib:0.0.1")  // 공통모듈
```  
2. application.yml 설정
```
spring:
  datasource:
    master: # CUD 전용 - Service에 @Transactional
      driver-class-name: org.postgresql.Driver
      jdbc-url: jdbc:postgresql://220.86.29.33:35432/test_db
      read-only: false
      username: insoft
      password: insoft!23
    slave: # 읽기전용 - Service에 @Transactional(readOnly = true)
      driver-class-name: org.postgresql.Driver
      jdbc-url: jdbc:postgresql://172.30.10.26:5432/test_db
      read-only: true
      username: insoft
      password: insoft!23
```  
3. Service에 @Transactional 사용  
 생성,수정,삭제용 : @Transactional  
 읽기전용 : @Transactional(readOnly = true)  
```  
@Transactional(readOnly = true) 로 설정한 서비스에서 CUD 발생서 아래와 같은 오류 발생  
Cause: org.postgresql.util.PSQLException: ERROR: cannot execute DELETE in a read-only transaction
```  
 4. application.yml의 config을 사용하지 못하고 mybatis-config.xml을 사용해야함  
 예) camel case 문법은 mybatis-config.xml에 설정해야함  
 ``` 
 mybatis:  
   mapperLocations: mybatis/**/mapper/**/*.xml   
   configLocations: mybatis/mybatis-config.xml
 ```   
<!--
### ==== 아래 구조로 표준 잡아서 변경 예정 ====
### 프로젝트설명  
### 디펜던시(Technology stack)  
### 아키텍처 설명   
### 프로젝트 구조  
### API리스트
### Build & Run this application    
gradle build bootRun  
### Docker Build    
docker build --tag icnp-web-user:1.0.0
### Docker Run
docker run --rm -p 8080:8080 icnp-web-user:1.0.0
### Docker-compose Run
docker-compose up -d --build
-->
