package com.example.todokotlin.infra.model

import com.example.todokotlin.domain.model.todo.Content
import com.example.todokotlin.domain.model.todo.Priority
import com.example.todokotlin.domain.model.todo.Todo
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class TodoRow(
    @Id
    val id: Int,
    val content: String,
    val priority: String,
    val dueDate: LocalDateTime?,
) {
    fun toTodo(): Todo {
        return Todo.reconstruct(
            id = id,
            content = Content.from(content),
            priority = Priority.from(priority),
            dueDate = dueDate,
        )
    }
}
