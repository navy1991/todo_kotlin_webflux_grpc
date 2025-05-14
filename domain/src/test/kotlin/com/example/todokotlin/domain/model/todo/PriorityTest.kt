package com.example.todokotlin.domain.model.todo

import com.example.todokotlin.domain.exception.TodoKotlinException
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PriorityTest {
    @ParameterizedTest
    @ValueSource(strings = ["LOW", "MIDDLE", "HIGH"])
    @DisplayName("正常系：優先度の生成")
    fun testFrom(value: String) {
        // Act
        val actual = Priority.from(value)

        // Assert
        actual.name.shouldBe(value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["low", "dummy", ""])
    @DisplayName("異常系：優先度の生成エラー")
    fun testFromError(value: String) {
        // Arrange

        // Act
        val e = assertThrows<TodoKotlinException> { Priority.from(value) }

        // Assert
        e.code.shouldBe(TodoKotlinException.Code.INVALID_PRIORITY)
        e.level.shouldBe(TodoKotlinException.Level.WARN)
    }
}
