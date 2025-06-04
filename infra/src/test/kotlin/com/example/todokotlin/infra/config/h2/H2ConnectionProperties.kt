package com.example.todokotlin.infra.config.h2

import io.r2dbc.h2.H2ConnectionConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * https://github.com/r2dbc/r2dbc-h2
 */
@ConditionalOnProperty(prefix = "h2", name = ["user"])
@ConfigurationProperties(prefix = "h2", ignoreInvalidFields = true)
data class H2ConnectionProperties(
    val user: String,
    val password: String,
    val database: String,
    val option: String,
) {
    val configuration: H2ConnectionConfiguration by lazy {
        H2ConnectionConfiguration.builder()
            .username(user)
            .password(password)
            .inMemory(database)
            .option(option)
            .build()
    }
}
