plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.springdoc.openapi-gradle-plugin") version "1.9.0"
}

group = "dev.meirong"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-security")
	// https://github.com/spring-projects/spring-boot/issues/39753
	// can not use 3.2.0 now
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0"){
		exclude(group = "xml-apis", module = "xml-apis")
	}

	implementation("com.auth0:java-jwt:4.4.0")

	// implementation("org.hibernate:hibernate-core:6.6.0.Final")
	// runtimeOnly("com.h2database:h2")
	runtimeOnly("mysql:mysql-connector-java:8.0.33")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testRuntimeOnly("com.h2database:h2")
	testImplementation("org.springframework.security:spring-security-test")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
