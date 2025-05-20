package com.example.todokotlin.domain.exception

open class TodoKotlinException(
    val code: Code,
    val level: Level,
    message: String?,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    enum class Code {
        EMPTY_ID,

        // EnumType
        INVALID_SAVE_RESULT,

        // todo
        INVALID_CONTENT,
        INVALID_PRIORITY,
    }

    enum class Level {
        ERROR,
        WARN,
    }
}
