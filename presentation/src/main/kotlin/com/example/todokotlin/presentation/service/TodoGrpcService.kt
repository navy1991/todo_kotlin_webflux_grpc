package com.example.todokotlin.presentation.service

import com.example.todokotlin.app.usecase.todo.CreateTodoUseCase
import com.example.todokotlin.app.usecase.todo.CreateTodoUseCaseInput
import com.example.todokotlin.presentation.extensions.toLocalDateTime
import com.example.todokotlin.presentation.extensions.toTimestamp
import com.example.todokotlin.proto.CreateTodoRequest
import com.example.todokotlin.proto.CreateTodoResponse
import com.example.todokotlin.proto.TodoServiceGrpcKt
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class TodoGrpcService(
    private val createTodoUseCase: CreateTodoUseCase,
) : TodoServiceGrpcKt.TodoServiceCoroutineImplBase() {

    override suspend fun createTodo(request: CreateTodoRequest): CreateTodoResponse {
        val output = createTodoUseCase.execute(
            input = CreateTodoUseCaseInput(
                content = request.content,
                priority = request.priority.ifEmpty { null },
                dueDate = if (request.hasDueDate()) request.dueDate.toLocalDateTime() else null,
            )
        )

        // kotlin DSLで書きたい場合はbuf入れてprotoファイルのmessageを.ktファイル生成する(今は.javaのみ)
        return CreateTodoResponse
            .newBuilder()
            .apply {
                this.setId(output.id)
                this.setContent(output.content)
                this.setPriority(output.priority)
                if (output.dueDate != null) {
                    this.setDueDate(output.dueDate!!.toTimestamp())
                }
            }
            .build()
    }
}
