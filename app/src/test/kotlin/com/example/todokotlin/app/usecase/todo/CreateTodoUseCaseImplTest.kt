package com.example.todokotlin.app.usecase.todo

import com.example.todokotlin.domain.model.todo.Content
import com.example.todokotlin.domain.model.todo.Priority
import com.example.todokotlin.domain.model.todo.Todo
import com.example.todokotlin.domain.repository.TodoRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class CreateTodoUseCaseImplTest {
    @MockK
    private lateinit var todoRepository: TodoRepository

    @InjectMockKs
    private lateinit var createTodoUseCase: CreateTodoUseCaseImpl

    @Test
    fun `success - create todo`() = runTest {
        // Arrange
        val input = CreateTodoUseCaseInput(
            content = "test",
            priority = "MIDDLE",
            dueDate = LocalDateTime.of(2025, 5, 20, 12, 30, 10)
        )
        coEvery { todoRepository.save(any()) } returns Todo.reconstruct(
            id = 1,
            content = Content.from(input.content),
            priority = Priority.from(input.priority!!),
            dueDate = input.dueDate,
        )

        // Act
        val actual = createTodoUseCase.execute(input)

        // Assert
        actual.content.shouldBe(input.content)
        actual.priority.shouldBe(input.priority)
        actual.dueDate.shouldBe(input.dueDate)
    }
}
