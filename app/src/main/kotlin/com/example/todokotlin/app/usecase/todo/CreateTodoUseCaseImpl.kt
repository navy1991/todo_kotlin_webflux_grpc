package com.example.todokotlin.app.usecase.todo

import com.example.todokotlin.domain.model.todo.Content
import com.example.todokotlin.domain.model.todo.Priority
import com.example.todokotlin.domain.model.todo.Todo
import com.example.todokotlin.domain.repository.TodoRepository
import org.springframework.stereotype.Component

@Component
internal class CreateTodoUseCaseImpl(
    private val todoRepository: TodoRepository,
) : CreateTodoUseCase {
    override suspend fun execute(input: CreateTodoUseCaseInput): CreateTodoUseCaseOutput {
        val todo = Todo.add(
            content = Content.from(input.content),
            priority = input.priority?.let { Priority.from(it) },
            dueDate = input.dueDate,
        )

        return todoRepository.save(todo).let {
            CreateTodoUseCaseOutput(
                id = it.id!!,
                content = it.content.value,
                priority = it.priority.name,
                dueDate = it.dueDate,
            )
        }
    }
}
