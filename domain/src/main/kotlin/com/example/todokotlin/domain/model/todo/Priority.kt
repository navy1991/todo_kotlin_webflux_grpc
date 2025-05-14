package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException

enum class Priority {
    LOW,
    MIDDLE,
    HIGH,
    ;

    companion object {
        fun from(value: String): Priority {
            return Priority.entries.find { it.name == value }
                ?: throw TodoKotlinException(
                    code = TodoKotlinException.Code.INVALID_PRIORITY,
                    level = TodoKotlinException.Level.WARN,
                    message = "the priority value is invalid. value:$value"
                )
        }
    }
}
