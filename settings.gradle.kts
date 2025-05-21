rootProject.name = "todo-kotlin-webflux-grpc"

pluginManagement {
    includeBuild("build-logic")
}

include(
    ":domain",
    ":app",
    ":infra",
)
