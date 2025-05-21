package com.example.todokotlin.infra.config.mysql

import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

/**
 * https://github.com/asyncer-io/r2dbc-mysql
 */
@ConditionalOnProperty(prefix = "mysql", name = ["host"])
@ConfigurationProperties(prefix = "mysql")
data class MySqlConnectionProperties(
    val host: String,
    val user: String,
    val port: Int,
    val password: String,
    val database: String,
    val connectionTimeout: Long,
) {
    val options: ConnectionFactoryOptions by lazy {
        ConnectionFactoryOptions.builder()
            .option(ConnectionFactoryOptions.DRIVER, "mysql")
            .option(ConnectionFactoryOptions.HOST, host)
            .option(ConnectionFactoryOptions.USER, user)
            .option(ConnectionFactoryOptions.PORT, port)
            .option(ConnectionFactoryOptions.PASSWORD, password)
            .option(ConnectionFactoryOptions.DATABASE, database)
            .option(ConnectionFactoryOptions.CONNECT_TIMEOUT, Duration.ofSeconds(connectionTimeout))
            .build()
    }
}
