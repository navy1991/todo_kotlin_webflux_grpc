plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("com.google.protobuf") version "0.9.4"
	id("java")
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
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
	implementation("io.grpc:grpc-protobuf:1.63.0")
	implementation("io.grpc:grpc-stub:1.63.0")
	implementation("io.grpc:grpc-netty-shaded:1.63.0")
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
		artifact = "com.google.protobuf:protoc:4.30.2"
	}
	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.builtins {
				create("kotlin")
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
