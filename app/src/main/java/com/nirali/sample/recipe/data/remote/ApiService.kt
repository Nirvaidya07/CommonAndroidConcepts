package com.nirali.sample.recipe.data.remote

import com.nirali.interview.domain.model.BreedResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v2/breeds")
    suspend fun getBreeds(): Response<BreedResponse>




}