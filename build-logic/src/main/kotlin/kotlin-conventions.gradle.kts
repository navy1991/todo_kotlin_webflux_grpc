import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    id("common-conventions")
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.kotlinx.kover")
}

val javaVersion: String = libs.findVersion("jvm").get().requiredVersion

dependencies {
    api(platform(libs.findLibrary("kotlinx-coroutines-bom").get()))
    api(platform(libs.findLibrary("kotest-bom").get()))

    implementation(libs.findBundle("kotlin").get())
    implementation(libs.findBundle("kotlin-coroutines").get())

    testImplementation(libs.findBundle("kotlin-test").get())

    detektPlugins(libs.findLibrary("detekt-formatting").get())
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

// https://github.com/detekt/detekt?tab=readme-ov-file#with-gradle
detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("${rootProject.projectDir}/config/detekt.yml")
    baseline = file("${rootProject.projectDir}/config/baseline.xml")
    autoCorrect = true // by detekt-formatting
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = javaVersion
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = javaVersion
}

// https://detekt.dev/docs/gettingstarted/gradle#gradle-runtime-dependencies
configurations.matching { it.name != "detekt" }.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion(libs.findVersion("detektKotlinVersion").get().requiredVersion)
        }
    }
}
