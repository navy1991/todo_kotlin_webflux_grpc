plugins {
    id("spring-boot-conventions")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.plugin.spring) apply false
    alias(libs.plugins.kotlin.plugin.noarg) apply false
    alias(libs.plugins.kover)
    alias(libs.plugins.sonarqube)
}

repositories {
    mavenCentral()
}

dependencies {
    // https://kotlin.github.io/kotlinx-kover/gradle-plugin/#multi-module-kotlin-jvm-project
    kover(project(":domain"))
    kover(project(":infra"))
    kover(project(":app"))
}

sonar {
    properties {
        property("sonar.projectKey", "navy1991_todo_kotlin_webflux_grpc")
        property("sonar.organization", "h-isawa")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/report.xml")
    }
}
