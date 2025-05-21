plugins {
    id("kotlin-conventions")
    kotlin("plugin.spring")
}

dependencies {
    implementation(libs.findBundle("spring-boot-starter").get())

    testImplementation(libs.findLibrary("spring-boot-starter-test").get()) {
        // https://github.com/Ninja-Squad/springmockk?tab=readme-ov-file#usage
        exclude(module = "mockito-core")
    }
    testImplementation(libs.findLibrary("springmockk").get())
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}
