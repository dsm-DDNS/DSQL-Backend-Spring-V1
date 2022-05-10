import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com.ddns"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //WebStarter
    implementation("org.springframework.boot:spring-boot-starter-web")
    //batch
    implementation("org.springframework.boot:spring-boot-starter-batch")
    //Mysql
    runtimeOnly("mysql:mysql-connector-java")
    //Komoran
    implementation("com.github.shin285:KOMORAN:3.3.4")
    //Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
    //AWS
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
    //Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    //Jwts
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    //data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    //mailing
    implementation("org.springframework.boot:spring-boot-starter-mail")
    //thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    //redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
