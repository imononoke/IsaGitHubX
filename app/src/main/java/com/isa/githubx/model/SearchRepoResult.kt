package com.isa.githubx.model

data class SearchRepoResult(
    val incomplete_results: Boolean,
    val items: List<RepoEntity>,
    val total_count: Int
)

enum class SearchType(val type: String) {
    REPO("repo"),
    USER("user")
}
