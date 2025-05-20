plugins {
    id("spring-boot-conventions")
    id("org.flywaydb.flyway")
}

dependencies {
    api(libs.findLibrary("spring-boot-starter-data-r2dbc").get())
    testImplementation(libs.findLibrary("flyway-core").get())
    testImplementation(libs.findLibrary("h2").get())
    testImplementation(libs.findLibrary("r2dbc-h2").get())
}
