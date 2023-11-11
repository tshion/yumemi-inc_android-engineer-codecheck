package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * A GitHub user.
 *
 * @param name
 * @param email
 * @param login
 * @param id
 * @param nodeId
 * @param avatarUrl
 * @param gravatarId
 * @param url
 * @param htmlUrl
 * @param followersUrl
 * @param followingUrl
 * @param gistsUrl
 * @param starredUrl
 * @param subscriptionsUrl
 * @param organizationsUrl
 * @param reposUrl
 * @param eventsUrl
 * @param receivedEventsUrl
 * @param type
 * @param siteAdmin
 * @param starredAt
 */
public data class NullableSimpleUser(
    val name: String? = null,
    val email: String? = null,
    val login: String,
    val id: Int,
    val nodeId: String,
    val avatarUrl: String,
    val gravatarId: String,
    val url: String,
    val htmlUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val gistsUrl: String,
    val starredUrl: String,
    val subscriptionsUrl: String,
    val organizationsUrl: String,
    val reposUrl: String,
    val eventsUrl: String,
    val receivedEventsUrl: String,
    val type: String,
    val siteAdmin: Boolean,
    val starredAt: String? = null
)
