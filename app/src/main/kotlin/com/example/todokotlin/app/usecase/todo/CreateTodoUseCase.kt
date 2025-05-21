package com.example.todokotlin.app.usecase.todo

import java.time.LocalDateTime

interface CreateTodoUseCase {
    suspend fun execute(input: CreateTodoUseCaseInput): CreateTodoUseCaseOutput
}

data class CreateTodoUseCaseInput(
    val content: String,
    val priority: String?,
    val dueDate: LocalDateTime?,
)

data class CreateTodoUseCaseOutput(
    val id: Int,
    val content: String,
    val priority: String,
    val dueDate: LocalDateTime?,
)
