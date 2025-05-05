plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("com.google.protobuf") version "0.9.4"
	id("java")
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.sonarqube") version "6.0.1.5171"
}

group = "com.example"
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
	implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.grpc:grpc-protobuf:1.72.0")
	implementation("io.grpc:grpc-stub:1.72.0")
	implementation("io.grpc:grpc-netty-shaded:1.72.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.25.1"
	}
	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.builtins {
				create("kotlin")
			}
		}
	}
}

sonar {
	properties {
		property("sonar.projectKey", "navy1991_todo_kotlin_webflux_grpc")
		property("sonar.organization", "h-isawa")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
