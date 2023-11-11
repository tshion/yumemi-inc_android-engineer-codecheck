package io.github.tshion.android.codecheck.github.webapi.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

internal class OffsetDateTimeAdapter {
    @ToJson
    fun toJson(value: OffsetDateTime): String {
//        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(value)
        return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(value)
    }

    @FromJson
    fun fromJson(value: String): OffsetDateTime {
//        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        return OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}
