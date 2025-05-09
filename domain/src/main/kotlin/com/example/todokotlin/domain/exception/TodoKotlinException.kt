package com.example.todokotlin.domain.exception

open class TodoKotlinException(
    val code: Code,
    val level: Level,
    message: String?,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    enum class Code {
        NOT_FOUND,

        // todo
        INVALID_CONTENT,
    }

    enum class Level {
        ERROR,
        WARN,
    }
}
