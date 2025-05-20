package com.example.todokotlin.infra.model

import com.example.todokotlin.domain.exception.TodoKotlinException
import com.example.todokotlin.domain.model.todo.Content
import com.example.todokotlin.domain.model.todo.Priority
import com.example.todokotlin.domain.model.todo.Todo
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("todos")
data class TodoRow(
    @Id
    val id: Int?,
    val content: String,
    val priority: String,
    val dueDate: LocalDateTime?,
) {
    /**
     * DBからの再構築
     */
    fun toTodo(): Todo {
        // 再構築時はIDがある前提
        if (id == null) {
            throw TodoKotlinException(
                code = TodoKotlinException.Code.EMPTY_ID,
                level = TodoKotlinException.Level.ERROR,
                message = "Id value is null."
            )
        }

        return Todo.reconstruct(
            id = id,
            content = Content.from(content),
            priority = Priority.from(priority),
            dueDate = dueDate,
        )
    }
}
