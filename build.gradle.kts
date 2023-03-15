val nexus_username: String by project
val nexus_password: String by project
val nexusURL: String by project

plugins {
    java
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "icnp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven {
        url = uri( nexusURL )
        isAllowInsecureProtocol = true

        credentials {
            username = nexus_username
            password = nexus_password
        }
    }
}

extra["springCloudVersion"] = "2022.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")                  // spring-boot default
    implementation("org.springframework.boot:spring-boot-starter-validation")           // spring-boot default
    implementation("org.projectlombok:lombok")                                          // spring-boot default
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")         // mybatis
    implementation("org.springframework.boot:spring-boot-starter-log4j2")               // log4j2
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.10.3")   // log4j2
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.1")    // openfeign(연계호출)용
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")           // swagger용
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")            // 추후 react 사용시 제거
    implementation("icnp:icnp-common-lib:0.0.2")                                        // 공통모듈
    implementation("org.springframework.boot:spring-boot-starter-jdbc")                 // HikariDataSource
//    implementation("io.springfox:springfox-swagger2:2.9.2")
//    implementation("io.springfox:springfox-swagger-ui:2.9.2")
//    implementation("io.swagger:swagger-annotations:1.5.21")
//    implementation("io.swagger:swagger-models:1.5.21")
    testImplementation("org.projectlombok:lombok")
    testImplementation("junit:junit")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor") // DB RW 분리
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.springframework.boot:spring-boot-starter-test")
    testAnnotationProcessor("org.projectlombok:lombok")

    modules {   // log4j2 사용시 logback 모듈 제거용
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2", "Use Log4j2 instead of Logback")
        }
    }
}

dependencyManagement {
    imports {   // spring cloud 환경
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

configurations.all  { // log4j2 사용시 starter logging 제외
    exclude("org.springframework.boot:spring-boot-starter-logging")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
