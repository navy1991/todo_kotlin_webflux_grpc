package com.example.todokotlin.infra.crudrepository

import com.example.todokotlin.infra.model.TodoRow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TodoCrudRepository : CoroutineCrudRepository<TodoRow, Int>
