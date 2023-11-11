package io.github.tshion.android.codecheck.github.webapi.entities

/**
 * Repo Search Result Item
 *
 * @param id
 * @param nodeId
 * @param name
 * @param fullName
 * @param owner
 * @param &#x60;private&#x60;
 * @param htmlUrl
 * @param description
 * @param fork
 * @param url
 * @param createdAt
 * @param updatedAt
 * @param pushedAt
 * @param homepage
 * @param size
 * @param stargazersCount
 * @param watchersCount
 * @param language
 * @param forksCount
 * @param openIssuesCount
 * @param masterBranch
 * @param defaultBranch
 * @param score
 * @param forksUrl
 * @param keysUrl
 * @param collaboratorsUrl
 * @param teamsUrl
 * @param hooksUrl
 * @param issueEventsUrl
 * @param eventsUrl
 * @param assigneesUrl
 * @param branchesUrl
 * @param tagsUrl
 * @param blobsUrl
 * @param gitTagsUrl
 * @param gitRefsUrl
 * @param treesUrl
 * @param statusesUrl
 * @param languagesUrl
 * @param stargazersUrl
 * @param contributorsUrl
 * @param subscribersUrl
 * @param subscriptionUrl
 * @param commitsUrl
 * @param gitCommitsUrl
 * @param commentsUrl
 * @param issueCommentUrl
 * @param contentsUrl
 * @param compareUrl
 * @param mergesUrl
 * @param archiveUrl
 * @param downloadsUrl
 * @param issuesUrl
 * @param pullsUrl
 * @param milestonesUrl
 * @param notificationsUrl
 * @param labelsUrl
 * @param releasesUrl
 * @param deploymentsUrl
 * @param gitUrl
 * @param sshUrl
 * @param cloneUrl
 * @param svnUrl
 * @param forks
 * @param openIssues
 * @param watchers
 * @param topics
 * @param mirrorUrl
 * @param hasIssues
 * @param hasProjects
 * @param hasPages
 * @param hasWiki
 * @param hasDownloads
 * @param hasDiscussions
 * @param archived
 * @param disabled Returns whether or not this repository disabled.
 * @param visibility The repository visibility: public, private, or internal.
 * @param license
 * @param permissions
 * @param textMatches
 * @param tempCloneToken
 * @param allowMergeCommit
 * @param allowSquashMerge
 * @param allowRebaseMerge
 * @param allowAutoMerge
 * @param deleteBranchOnMerge
 * @param allowForking
 * @param isTemplate
 * @param webCommitSignoffRequired
 */
public data class RepoSearchResultItem(
    val id: Int,
    val nodeId: String,
    val name: String,
    val fullName: String,
    val owner: NullableSimpleUser,
    val `private`: Boolean,
    val htmlUrl: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val createdAt: java.time.LocalDateTime,
    val updatedAt: java.time.LocalDateTime,
    val pushedAt: java.time.LocalDateTime,
    val homepage: String,
    val size: Int,
    val stargazersCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
    val openIssuesCount: Int,
    val masterBranch: String? = null,
    val defaultBranch: String,
    val score: java.math.BigDecimal,
    val forksUrl: String,
    val keysUrl: String,
    val collaboratorsUrl: String,
    val teamsUrl: String,
    val hooksUrl: String,
    val issueEventsUrl: String,
    val eventsUrl: String,
    val assigneesUrl: String,
    val branchesUrl: String,
    val tagsUrl: String,
    val blobsUrl: String,
    val gitTagsUrl: String,
    val gitRefsUrl: String,
    val treesUrl: String,
    val statusesUrl: String,
    val languagesUrl: String,
    val stargazersUrl: String,
    val contributorsUrl: String,
    val subscribersUrl: String,
    val subscriptionUrl: String,
    val commitsUrl: String,
    val gitCommitsUrl: String,
    val commentsUrl: String,
    val issueCommentUrl: String,
    val contentsUrl: String,
    val compareUrl: String,
    val mergesUrl: String,
    val archiveUrl: String,
    val downloadsUrl: String,
    val issuesUrl: String,
    val pullsUrl: String,
    val milestonesUrl: String,
    val notificationsUrl: String,
    val labelsUrl: String,
    val releasesUrl: String,
    val deploymentsUrl: String,
    val gitUrl: String,
    val sshUrl: String,
    val cloneUrl: String,
    val svnUrl: String,
    val forks: Int,
    val openIssues: Int,
    val watchers: Int,
    val topics: List<String>? = null,
    val mirrorUrl: String,
    val hasIssues: Boolean,
    val hasProjects: Boolean,
    val hasPages: Boolean,
    val hasWiki: Boolean,
    val hasDownloads: Boolean,
    val hasDiscussions: Boolean? = null,
    val archived: Boolean,
    /* Returns whether or not this repository disabled. */
    val disabled: Boolean,
    /* The repository visibility: public, private, or internal. */
    val visibility: String? = null,
    val license: NullableLicenseSimple,
    val permissions: ReposearchresultitemPermissions? = null,
//    val textMatches: SearchResultTextMatches? = null,
    val tempCloneToken: String? = null,
    val allowMergeCommit: Boolean? = null,
    val allowSquashMerge: Boolean? = null,
    val allowRebaseMerge: Boolean? = null,
    val allowAutoMerge: Boolean? = null,
    val deleteBranchOnMerge: Boolean? = null,
    val allowForking: Boolean? = null,
    val isTemplate: Boolean? = null,
    val webCommitSignoffRequired: Boolean? = null
)
