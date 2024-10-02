plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.job"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-webflux") {
//		exclude("io.netty", "netty-codec-http")
	}

//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.postgresql:r2dbc-postgresql:1.0.5.RELEASE")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.flywaydb:flyway-core:10.18.2")
	implementation("org.flywaydb:flyway-database-postgresql:10.18.2")
	implementation("org.postgresql:postgresql")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.slf4j:slf4j-api:2.0.0")

	implementation("org.springframework.kafka:spring-kafka")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.mockk:mockk:1.13.3")
	testImplementation("org.springframework.boot:spring-boot-test")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
	testImplementation("io.kotest:kotest-assertions-core:5.6.0")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
