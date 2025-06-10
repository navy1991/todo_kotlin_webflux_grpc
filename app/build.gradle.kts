group = "com.example.todokotlin.app"

plugins {
    id("spring-boot-conventions")
}

dependencies {
    implementation(project(":domain"))
    testImplementation(project(":infra"))
}

tasks.withType<Test> {
    jvmArgs("-Djdk.attach.allowAttachSelf=true")
}
