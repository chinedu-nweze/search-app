package com.search.app.repository

import com.search.app.model.Data
import com.search.app.model.DataResult

interface ISearchRickAndMortyRepository {
    suspend fun getSearchResult(searchQuery: String): DataResult<Data>
}