package com.nirali.sample.recipe.domain.repository

import com.nirali.dogBreed.domain.model.BreedResponse
import com.nirali.sample.recipe.components.Resource
import com.nirali.sample.recipe.domain.model.Data

interface RecipeRepository {

    suspend fun getRecipes(): Resource<BreedResponse>

    suspend fun searchRecipes(query: String, apiKey: String): Resource<BreedResponse>

    suspend fun insertRecipe(recipe: Data)

    suspend fun deleteRecipe(recipe: Data)





}