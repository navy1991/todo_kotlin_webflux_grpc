package com.example.todokotlin.infra.config.h2

import io.r2dbc.h2.H2ConnectionFactory
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.H2Dialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
@Profile("test")
@EnableConfigurationProperties(
    H2ConnectionProperties::class,
    FlywayProperties::class,
)
@EnableR2dbcRepositories(
    basePackages = [
        "com.example.todokotlin.infra.crudrepository",
    ],
    entityOperationsRef = "entityOperations"
)
class H2Config {
    @Bean
    fun connectionFactory(properties: H2ConnectionProperties): H2ConnectionFactory {
        return H2ConnectionFactory(properties.configuration)
    }

    @Bean
    fun transactionManager(connectionFactory: H2ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    fun entityOperations(connectionFactory: H2ConnectionFactory): R2dbcEntityOperations {
        return R2dbcEntityTemplate(
            DatabaseClient.create(connectionFactory),
            H2Dialect.INSTANCE,
        )
    }

    @Bean(initMethod = "migrate", name = ["flywayH2"])
    @ConditionalOnProperty(prefix = "spring.flyway", name = ["enabled"], matchIfMissing = false)
    fun flywayH2(
        flywayProperties: FlywayProperties,
        h2ConnectionProperties: H2ConnectionProperties,
    ): Flyway {
        val config = Flyway.configure()
            .dataSource(
                flywayProperties.url,
                h2ConnectionProperties.user,
                h2ConnectionProperties.password,
            )
            .locations("db/migration")
        return config.load()
    }
}
