package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException

/**
 * TODOの内容
 */
@JvmInline
value class Content private constructor(val value: String) {
    init {
        if (value.length > MAX_LENGTH) {
            throw TodoKotlinException(
                code = TodoKotlinException.Code.INVALID_CONTENT,
                level = TodoKotlinException.Level.WARN,
                message = "The length of the content exceeds $MAX_LENGTH.",
            )
        }
    }

    companion object {
        private const val MAX_LENGTH = 50

        /**
         * TODOの内容を生成
         */
        fun from(value: String) = Content(value = value)
    }
}
