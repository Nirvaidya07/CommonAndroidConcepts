package com.nirali.interview_api_call.data.remote

import com.nirali.interview_api_call.domain.model.NewsResponse
import com.nirali.interview_api_call.presentation.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getTrendingListAnime(
        @Query("country") country: String = "us",
        @Query("category") category: String="business",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}