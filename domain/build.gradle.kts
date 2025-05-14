plugins {
    id("spring-boot-conventions")
}

group = "com.example.todokotlin.domain"

dependencies {
    implementation(libs.spring.boot.starter.webflux)
    testImplementation(libs.bundles.spring.boot.test)
    testImplementation(kotlin("test"))
}
