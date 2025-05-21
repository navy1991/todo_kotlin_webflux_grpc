group = "com.example.todokotlin.app"

plugins {
    id("spring-boot-conventions")
}

dependencies {
    implementation(project(":domain"))
    testImplementation(project(":infra"))
}
