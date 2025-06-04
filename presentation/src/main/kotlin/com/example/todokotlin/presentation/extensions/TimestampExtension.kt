package com.example.todokotlin.presentation.extensions

import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun Timestamp.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return Instant.ofEpochSecond(this.seconds, this.nanos.toLong())
        .atZone(zoneId)
        .toLocalDateTime()
}
