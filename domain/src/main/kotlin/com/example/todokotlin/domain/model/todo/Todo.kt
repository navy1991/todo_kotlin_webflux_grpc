package com.example.todokotlin.domain.model.todo

import java.time.LocalDateTime

class Todo private constructor(
    val id: Int?,
    content: Content,
    priority: Priority,
    dueDate: LocalDateTime?,
) {
    /** Todoの内容 */
    var content: Content = content
        private set

    /** 優先度 */
    var priority: Priority = priority
        private set

    /** 期限日 */
    var dueDate: LocalDateTime? = dueDate
        private set

    /**
     * Todoを更新
     */
    fun update(content: Content, priority: Priority, dueDate: LocalDateTime?) {
        this.content = content
        this.priority = priority
        this.dueDate = dueDate
    }

    companion object {
        /** Todoの追加 */
        fun add(
            content: Content,
            priority: Priority = Priority.MIDDLE,
            dueDate: LocalDateTime? = null,
        ) = Todo(
            id = null,
            content = content,
            priority = priority,
            dueDate = dueDate,
        )

        /** リポジトリからの再構築用 */
        fun reconstruct(
            id: Int,
            content: Content,
            priority: Priority,
            dueDate: LocalDateTime?,
        ) = Todo(
            id = id,
            content = content,
            priority = priority,
            dueDate = dueDate,
        )
    }
}
