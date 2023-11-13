package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * License Simple
 *
 * @param key
 * @param name
 * @param url
 * @param spdx_id
 * @param node_id
 * @param html_url
 */
public data class NullableLicenseSimple(
    val key: String,
    val name: String,
    val url: String?,
    val spdx_id: String?,
    val node_id: String,
    val html_url: String? = null
)
