package com.example.todokotlin.infra.repository

import com.example.todokotlin.domain.enumtype.SaveResult
import com.example.todokotlin.domain.model.todo.Todo
import com.example.todokotlin.domain.repository.TodoRepository
import com.example.todokotlin.infra.crudrepository.TodoCrudRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl(
    private val todoCrudRepository: TodoCrudRepository,
) : TodoRepository {
    override suspend fun findOne(id: Int): Todo? {
        return todoCrudRepository.findOne(id).awaitSingleOrNull()?.toTodo()
    }

    override suspend fun findAll(): List<Todo> {
        return todoCrudRepository.findAll().map { it.toTodo() }.toList()
    }

    override suspend fun save(todo: Todo): SaveResult {
        return todoCrudRepository.upsert(
            id = todo.id,
            content = todo.content.value,
            priority = todo.priority.name,
            dueDate = todo.dueDate,
        ).awaitSingle().let { SaveResult.from(it) }
    }

    override suspend fun delete(id: Int): Int {
        return todoCrudRepository.delete(id).awaitSingle()
    }
}
