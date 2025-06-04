package com.example.todokotlin.app.usecase

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.r2dbc.core.DatabaseClient

@SpringBootTest(classes = [ConfigurationForUseCaseTest::class])
abstract class UseCaseTestBase {
    private lateinit var connectionFactory: ConnectionFactory

    protected val databaseClient: DatabaseClient by lazy {
        DatabaseClient.create(connectionFactory)
    }
}

@SpringBootConfiguration
@ComponentScan(basePackages = ["com.example.todokotlin"])
class ConfigurationForUseCaseTest
