package com.example.todokotlin.infra.config.mysql

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.MySqlDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
@Profile("!test")
@EnableConfigurationProperties(MySqlConnectionProperties::class)
@EnableR2dbcRepositories(
    basePackages = [
        "com.example.todokotlin.infra.crudrepository",
    ],
    entityOperationsRef = "entityOperations"
)
class MySqlConfig {
    @Bean
    fun connectionFactory(properties: MySqlConnectionProperties): ConnectionFactory {
        return ConnectionFactories.get(properties.options)
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    fun entityOperations(connectionFactory: ConnectionFactory): R2dbcEntityOperations {
        return R2dbcEntityTemplate(
            DatabaseClient.create(connectionFactory),
            MySqlDialect.INSTANCE,
        )
    }
}
