package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ContentTest {
    @Test
    fun `success - create Content`() {
        // Arrange
        val value = "あ".repeat(MAX_LENGTH)

        // Act
        val actual = Content.from(value = value)

        // Assert
        actual.value.shouldBe(value)
    }

    @Test
    fun `failure - create Content - maximum number of characters exceeded`() {
        // Arrange
        val value = "あ".repeat(MAX_LENGTH + 1)

        // Act
        val e = shouldThrow<TodoKotlinException> { Content.from(value = value) }

        // Assert
        e.code.shouldBe(TodoKotlinException.Code.INVALID_CONTENT)
        e.level.shouldBe(TodoKotlinException.Level.WARN)
        e.message.shouldBe("The length of the content exceeds 50.")
    }

    companion object {
        private const val MAX_LENGTH = 50
    }
}
