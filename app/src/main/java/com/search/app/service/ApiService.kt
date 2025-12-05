package com.search.app.service

import com.search.app.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /**
     * Get the list of the characters from the API
     * The @Query annotation is used to add the query parameter to the URL
     */
    @GET("character")
    suspend fun getSearchResult(@Query("name") searchQuery: String): Response<Data>
}