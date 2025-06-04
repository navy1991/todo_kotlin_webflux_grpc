package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PriorityTest {
    @ParameterizedTest
    @ValueSource(strings = ["LOW", "MIDDLE", "HIGH"])
    fun `success - create Priority`(value: String) {
        // Act
        val actual = Priority.from(value)

        // Assert
        actual.name.shouldBe(value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["low", "dummy", ""])
    fun `failure - create Priority - invalid value`(value: String) {
        // Arrange

        // Act
        val e = shouldThrow<TodoKotlinException> { Priority.from(value) }

        // Assert
        e.code.shouldBe(TodoKotlinException.Code.INVALID_PRIORITY)
        e.level.shouldBe(TodoKotlinException.Level.WARN)
    }
}
