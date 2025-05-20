package com.example.todokotlin.infra.repository

import com.example.todokotlin.domain.model.todo.Content
import com.example.todokotlin.domain.model.todo.Priority
import com.example.todokotlin.domain.model.todo.Todo
import com.example.todokotlin.infra.config.h2.H2Config
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.r2dbc.spi.ConnectionFactory
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import java.time.LocalDateTime

@SpringBootTest(
    classes = [
        TodoRepositoryImpl::class,
        H2Config::class,
    ]
)
class TodoRepositoryImplTest {
    @Autowired
    private lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @Autowired
    private lateinit var connectionFactory: ConnectionFactory

    val databaseClient: DatabaseClient by lazy {
        DatabaseClient.create(connectionFactory)
    }

    @Test
    @DisplayName("正常系：Todoを取得")
    fun testFindOne() = runTest {
        // Arrange
        val todo = Todo.reconstruct(
            id = 1,
            content = Content.from("test"),
            priority = Priority.MIDDLE,
            dueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 10)
        )
        databaseClient.sql(
            """
                INSERT INTO todos (
                    id, content, priority, due_date
                ) VALUES (
                    ${todo.id}, '${todo.content.value}', '${todo.priority.name}', '2025-05-15 12:30:10'
                );
            """.trimIndent()
        ).fetch().awaitRowsUpdated()

        // Act
        val actual = todoRepositoryImpl.findOne(todo.id!!)

        // Assert
        actual.shouldNotBeNull().run {
            this.id.shouldBe(todo.id)
            this.content.shouldBe(todo.content)
            this.priority.shouldBe(todo.priority)
            this.dueDate.shouldBe(todo.dueDate)
        }
    }

    @Test
    @DisplayName("正常系：Todoを全件取得")
    fun testFindAll() = runTest {
        // Arrange
        val todoA = Todo.reconstruct(
            id = 2,
            content = Content.from("test"),
            priority = Priority.MIDDLE,
            dueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 10)
        )
        val todoB = Todo.reconstruct(
            id = 3,
            content = Content.from("test"),
            priority = Priority.MIDDLE,
            dueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 10)
        )
        databaseClient.sql(
            """
                INSERT INTO todos (
                    id, content, priority, due_date
                ) VALUES (
                    ${todoA.id}, '${todoA.content.value}', '${todoA.priority.name}', '2025-05-15 12:30:10'
                );
                INSERT INTO todos (
                    id, content, priority, due_date
                ) VALUES (
                    ${todoB.id}, '${todoB.content.value}', '${todoB.priority.name}', '2025-05-15 12:30:10'
                );
            """.trimIndent()
        ).fetch().awaitRowsUpdated()

        // Act
        val actual = todoRepositoryImpl.findAll()

        // Assert
        actual.shouldNotBeNull()
        actual.get(0).run {
            this.id.shouldBe(todoA.id)
            this.content.shouldBe(todoA.content)
            this.priority.shouldBe(todoA.priority)
            this.dueDate.shouldBe(todoA.dueDate)
        }
        actual.get(1).run {
            this.id.shouldBe(todoB.id)
            this.content.shouldBe(todoB.content)
            this.priority.shouldBe(todoB.priority)
            this.dueDate.shouldBe(todoB.dueDate)
        }
    }

    @Test
    @DisplayName("正常系：Todoを保存(作成)")
    fun testSaveAsCreate() = runTest {
        // Arrange
        val todo = Todo.add(
            content = Content.from("create"),
            priority = Priority.HIGH,
            dueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 10)
        )

        // Act
        val actual = todoRepositoryImpl.save(todo)

        // Assert
        actual.id.shouldNotBeNull()
        actual.content.shouldBe(todo.content)
        actual.priority.shouldBe(todo.priority)
        actual.dueDate.shouldBe(todo.dueDate)
    }

    @Test
    @DisplayName("正常系：Todoを保存(更新)")
    fun testSaveAsUpdate() = runTest {
        // Arrange
        val todo = Todo.reconstruct(
            id = 11,
            content = Content.from("update"),
            priority = Priority.LOW,
            dueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 10)
        )
        databaseClient.sql(
            """
                INSERT INTO todos (
                    id, content, priority, due_date
                ) VALUES (
                    ${todo.id}, '${todo.content.value}', '${todo.priority.name}', '2025-05-15 12:30:10'
                );
            """.trimIndent()
        ).fetch().awaitRowsUpdated()

        val newContent = Content.from("updated")
        val newPriority = Priority.HIGH
        val newDueDate = LocalDateTime.of(2025, 5, 15, 12, 30, 11)
        todo.update(
            content = newContent,
            priority = newPriority,
            dueDate = newDueDate
        )

        // Act
        val actual = todoRepositoryImpl.save(todo)

        // Assert
        actual.id.shouldBe(todo.id)
        actual.content.shouldBe(todo.content)
        actual.priority.shouldBe(todo.priority)
        actual.dueDate.shouldBe(todo.dueDate)
    }

    @Test
    @DisplayName("正常系：Todoを削除")
    fun testDelete() = runTest {
        // Arrange
        val id = 99
        databaseClient.sql(
            """
                INSERT INTO todos (
                    id, content, priority, due_date
                ) VALUES (
                    $id, 'test', 'MIDDLE', '2025-05-15 12:30:10'
                );
            """.trimIndent()
        ).fetch().awaitRowsUpdated()

        // Act
        assertDoesNotThrow { todoRepositoryImpl.delete(id) }
    }
}
