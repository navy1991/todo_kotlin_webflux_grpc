package com.example.todokotlin.infra.crudrepository

import com.example.todokotlin.infra.model.TodoRow
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface TodoCrudRepository : CoroutineCrudRepository<TodoRow, Int> {
    @Query(
        """
            SELECT
                id,
                content,
                priority,
                due_date
            FROM
                todos
            WHERE
                id = :id
        """,
    )
    fun findOne(id: Int): Mono<TodoRow>

    @Query(
        """
            SELECT
                id,
                content,
                priority,
                due_date
            FROM
                todos
        """,
    )
    override fun findAll(): Flow<TodoRow>

    @Modifying
    @Query(
        """
            INSERT INTO todos (
                id,
                content,
                priority,
                due_date
            ) VALUES (
                :id,
                :content,
                :priority,
                :dueDate
            )
            ON DUPLICATE KEY UPDATE
                content = VALUES(content),
                priority = VALUES(priority),
                due_date = VALUES(due_date)
        """,
    )
    fun upsert(id: Int, content: String, priority: String, dueDate: LocalDateTime?): Mono<Int>

    @Modifying
    @Query(
        """
            DELETE FROM
                todos
            WHERE
                id = :id
        """,
    )
    fun delete(id: Int): Mono<Int>
}
