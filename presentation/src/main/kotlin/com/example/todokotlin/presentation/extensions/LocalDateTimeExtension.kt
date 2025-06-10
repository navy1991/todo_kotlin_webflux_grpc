package com.example.todokotlin.presentation.extensions

import com.google.protobuf.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toTimestamp(zoneId: ZoneId = ZoneId.systemDefault()): Timestamp {
    val instant = this.atZone(zoneId).toInstant()
    return Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano)
        .build()
}
