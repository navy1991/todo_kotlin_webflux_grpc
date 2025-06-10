import com.google.protobuf.gradle.id

group = "com.example.todokotlin.presentation"

plugins {
    id("spring-boot-conventions")
    id("com.google.protobuf") version "0.9.5"
}

dependencies {
    implementation(platform(libs.spring.grpc.dependencies))
    implementation(project(":domain"))
    implementation(project(":app"))
    implementation(project(":infra"))
    implementation("io.grpc:grpc-services")
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.spring.boot.starter.grpc)
    implementation(libs.grpc.netty.shaded)
    modules {
        module("io.grpc:grpc-netty") {
            replacedBy("io.grpc:grpc-netty-shaded", "Use Netty shaded instead of regular Netty")
        }
    }

    testImplementation(libs.spring.grpc.test)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.31.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.71.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.3:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
                id("grpckt")
            }
        }
    }
}
