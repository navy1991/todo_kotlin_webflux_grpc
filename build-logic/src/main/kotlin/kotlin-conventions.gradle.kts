plugins {
    id("common-conventions")
    kotlin("jvm")
}

val javaVersion: String = libs.findVersion("jvm").get().requiredVersion

dependencies {
    api(platform(libs.findLibrary("spring-boot-dependencies").get()))
    api(platform(libs.findLibrary("kotest-bom").get()))

    implementation(libs.findBundle("kotlin").get())
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}
