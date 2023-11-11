package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * License Simple
 *
 * @param key
 * @param name
 * @param url
 * @param spdxId
 * @param nodeId
 * @param htmlUrl
 */
public data class NullableLicenseSimple(
    val key: String,
    val name: String,
    val url: String,
    val spdxId: String,
    val nodeId: String,
    val htmlUrl: String? = null
)
