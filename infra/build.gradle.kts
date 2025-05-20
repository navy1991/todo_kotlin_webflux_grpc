plugins {
    id("spring-boot-conventions")
    id("r2dbc-conventions")
}

group = "com.example.todokotlin.infra"

dependencies {
    implementation(project(":domain"))
}
