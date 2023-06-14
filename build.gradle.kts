import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.1.0" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    kotlin("jvm") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21" apply false
    kotlin("plugin.spring") version "1.8.21" apply false
    kotlin("plugin.allopen") version "1.8.21" apply false

}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects{
    group = "com.example"
    version = "0.0.1-SNAPSHOT"


    repositories {
        mavenCentral()
    }
}

subprojects{
    apply{
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.allopen")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")


    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
//        sourceSets.main {
//            withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//                kotlin.srcDir("$buildDir/generated/source/kapt/main")
//            }
//        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

}

//api <- core 의존
project(":multimodule-api") {
    dependencies {
        implementation(project(":multimodule-core"))
}

//core 설정
project(":multimodule-core") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("com.mysql:mysql-connector-j")

        // 쿼리 dsl
//        implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta") // 스프링부트 3.0 이상에서는 이렇게 변경 해줘야함.
//        kapt(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")
//        sourceSets.main {
//            withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//                kotlin.srcDir("$buildDir/generated/source/kapt/main")
//            }
//        }
//        annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
//        annotationProcessor ("jakarta.persistence:jakarta.persistence-api")

        // 쿼리 dsl
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
        implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
        implementation("com.infobip:infobip-spring-data-jpa-querydsl-boot-starter:8.1.1")
        kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
     }

    sourceSets.main {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDir("$buildDir/generated/source/kapt/main")
        }
    }

    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true
}





