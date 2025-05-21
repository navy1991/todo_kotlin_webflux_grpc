package com.example.todokotlin.infra.repository

import com.example.todokotlin.domain.model.todo.Todo
import com.example.todokotlin.domain.repository.TodoRepository
import com.example.todokotlin.infra.crudrepository.TodoCrudRepository
import com.example.todokotlin.infra.model.TodoRow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl(
    private val todoCrudRepository: TodoCrudRepository,
) : TodoRepository {
    override suspend fun findOne(id: Int): Todo? {
        return todoCrudRepository.findById(id)?.toTodo()
    }

    override suspend fun findAll(): List<Todo> {
        return todoCrudRepository.findAll().map { it.toTodo() }.toList()
    }

    override suspend fun save(todo: Todo): Todo {
        return todoCrudRepository.save(
            TodoRow(
                id = todo.id,
                content = todo.content.value,
                priority = todo.priority.name,
                dueDate = todo.dueDate,
            )
        ).toTodo()
    }

    override suspend fun delete(id: Int) {
        todoCrudRepository.deleteById(id)
    }
}
