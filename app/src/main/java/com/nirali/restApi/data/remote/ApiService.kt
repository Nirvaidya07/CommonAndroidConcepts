package com.nirali.restApi.data.remote

import com.nirali.restApi.domain.model.NewsResponse
import com.nirali.restApi.presentation.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getTrendingListAnime(
        @Query("country") country: String = "us",
        @Query("category") category: String="business",
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}