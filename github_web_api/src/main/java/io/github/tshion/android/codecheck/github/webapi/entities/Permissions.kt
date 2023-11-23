package io.github.tshion.android.codecheck.github.webapi.entities

import com.squareup.moshi.JsonClass

/**
 * @param admin
 * @param maintain
 * @param push
 * @param triage
 * @param pull
 */
@JsonClass(generateAdapter = true)
public data class Permissions(
    val admin: Boolean,
    val maintain: Boolean? = null,
    val push: Boolean,
    val triage: Boolean? = null,
    val pull: Boolean
)
