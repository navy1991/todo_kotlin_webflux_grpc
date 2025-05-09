plugins {
    id("kotlin-conventions")
    kotlin("plugin.spring")
}

dependencies {
    implementation(libs.findLibrary("spring-boot-starter-core").get())

    testImplementation(libs.findLibrary("spring-boot-starter-test").get())
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "test")
}
