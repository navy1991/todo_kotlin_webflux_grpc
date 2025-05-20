plugins {
    id("kotlin-conventions")
    kotlin("plugin.spring")
}

dependencies {
    implementation(libs.findBundle("spring-boot-starter").get())

    testImplementation(libs.findLibrary("spring-boot-starter-test").get())
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}
