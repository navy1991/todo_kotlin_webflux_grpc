package com.example.todokotlin.domain.enumtype

import com.example.todokotlin.domain.exception.TodoKotlinException

enum class SaveResult(val value: Int) {
    CREATED(1),
    UPDATED(2),
    SAME(0),
    ;

    companion object {
        fun from(value: Int): SaveResult {
            return SaveResult.entries.find { it.value == value }
                ?: throw TodoKotlinException(
                    code = TodoKotlinException.Code.INVALID_SAVE_RESULT,
                    level = TodoKotlinException.Level.WARN,
                    message = "the save result value is invalid. value:$value"
                )
        }
    }
}
