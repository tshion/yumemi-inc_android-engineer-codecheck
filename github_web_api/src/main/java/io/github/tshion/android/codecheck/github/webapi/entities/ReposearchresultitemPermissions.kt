package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * @param admin
 * @param maintain
 * @param push
 * @param triage
 * @param pull
 */
public data class ReposearchresultitemPermissions(
    val admin: Boolean,
    val maintain: Boolean? = null,
    val push: Boolean,
    val triage: Boolean? = null,
    val pull: Boolean
)
