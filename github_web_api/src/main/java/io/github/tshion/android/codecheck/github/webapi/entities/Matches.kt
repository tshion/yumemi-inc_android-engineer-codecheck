package io.github.tshion.android.codecheck.github.webapi.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class Matches(
    val text: String? = null,
    val indices: List<Int>? = null,
)
