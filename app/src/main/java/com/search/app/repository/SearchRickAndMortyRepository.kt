package com.search.app.repository

import com.search.app.model.Data
import com.search.app.model.DataResult
import com.search.app.service.ApiService
import timber.log.Timber

class SearchRickAndMortyRepository(
    private val apiService: ApiService,
) : ISearchRickAndMortyRepository {
    override suspend fun getSearchResult(searchQuery: String): DataResult<Data> {
        return try {
            val response = apiService.getSearchResult(searchQuery)
            if (response.isSuccessful) {
                Timber.d("Response: ${response.body()}")
                response.body()?.let { result ->
                    DataResult.Success(result)
                } ?: run {
                    DataResult.Error("No data found")
                }
            } else {
                Timber.w("Response: ${response.errorBody()}")
                DataResult.Error(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Timber.e(e)
            DataResult.Error(e.message)
        }
    }
}