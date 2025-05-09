package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ContentTest {
    @Test
    @DisplayName("正常系：Todoの内容を生成")
    fun testFrom() {
        // Arrange
        val value = "あ".repeat(MAX_LENGTH)

        // Act
        val actual = Content.from(value = value)

        // Assert
        actual.value.shouldBe(value)
    }

    @Test
    @DisplayName("異常系：Todoの内容を生成：最大文字数")
    fun testFromErrorOverMaxLength() {
        // Arrange
        val value = "あ".repeat(MAX_LENGTH + 1)

        // Act
        val e = assertThrows<TodoKotlinException> { Content.from(value = value) }

        // Assert
        e.code.shouldBe(TodoKotlinException.Code.INVALID_CONTENT)
        e.level.shouldBe(TodoKotlinException.Level.WARN)
        e.message.shouldBe("The length of the content exceeds 50.")
    }

    companion object {
        private const val MAX_LENGTH = 50
    }
}
