/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :12:05 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.data.repository

import com.nirali.dogBreed.domain.model.BreedResponse
import com.nirali.sample.recipe.components.Resource
import com.nirali.sample.recipe.components.safeApiCall
import com.nirali.sample.recipe.data.remote.ApiService
import com.nirali.sample.recipe.domain.model.Data
import com.nirali.sample.recipe.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: ApiService) : RecipeRepository {

    override suspend fun getRecipes(): Resource<BreedResponse> {

        return safeApiCall {
            apiService.getBreeds()
        }
    }

    override suspend fun searchRecipes(query: String, apiKey: String): Resource<BreedResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun insertRecipe(recipe: Data) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipe: Data) {
        TODO("Not yet implemented")
    }


}