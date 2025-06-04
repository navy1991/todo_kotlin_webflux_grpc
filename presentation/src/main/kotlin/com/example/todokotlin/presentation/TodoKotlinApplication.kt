package com.example.todokotlin.presentation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.todokotlin"])
class TodoKotlinApplication

fun main(args: Array<String>) {
    runApplication<TodoKotlinApplication>(args = args)
}
