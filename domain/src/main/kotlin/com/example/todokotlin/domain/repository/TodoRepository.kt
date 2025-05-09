package com.example.todokotlin.domain.repository

import com.example.todokotlin.domain.model.todo.Todo

/**
 * Todoリポジトリインターフェース
 */
interface TodoRepository {
    /**
     * Todoを取得
     */
    suspend fun findOne(id: Int): Todo?

    /**
     * Todoの一覧を取得
     */
    suspend fun findAll(): List<Todo>

    /**
     * Todoを保存
     */
    suspend fun save(todo: Todo)

    /**
     * Todoを削除
     */
    suspend fun delete(id: Int)
}
