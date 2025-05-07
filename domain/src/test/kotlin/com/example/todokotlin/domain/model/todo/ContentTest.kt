package com.example.todokotlin.domain.model.todo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ContentTest {
    @Test
    @DisplayName("正常系：TODOの内容を生成")
    fun testFrom() {
        // Arrange
        val value = "あ".repeat(MAX_LENGTH)

        // Act
        val actual = Content.from(value = value)

        // Assert
        assertEquals(value, actual.value)
    }

    companion object {
        private const val MAX_LENGTH = 50
    }
}
