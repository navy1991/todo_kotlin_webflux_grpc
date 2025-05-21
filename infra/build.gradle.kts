group = "com.example.todokotlin.infra"

plugins {
    id("spring-boot-conventions")
    id("r2dbc-conventions")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":app"))
}
