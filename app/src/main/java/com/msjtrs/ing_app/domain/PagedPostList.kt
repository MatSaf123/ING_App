package com.msjtrs.ing_app.domain


class PagedPostList<PostProperty> {

    private var page = 0
    private var results: List<PostProperty?> = ArrayList()
    private var totalResults = 0
    private var totalPages = 0

    fun getPage(): Int {
        return page
    }

    fun setPage(page: Int) {
        this.page = page
    }

    fun getResults(): List<PostProperty?>? {
        return results
    }

    fun setResults(results: List<PostProperty?>) {
        this.results = results
    }

    fun getTotalResults(): Int {
        return totalResults
    }

    fun setTotalResults(totalResults: Int) {
        this.totalResults = totalResults
    }

    fun getTotalPages(): Int {
        return totalPages
    }

    fun setTotalPages(totalPages: Int) {
        this.totalPages = totalPages
    }
}