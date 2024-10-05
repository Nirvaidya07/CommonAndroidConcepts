package com.nirali.auth_design.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nirali.auth_design.presentation.screens.LoginField
import com.nirali.expensetracker.core.presentation.home.screens.HomeScreen
import com.nirali.restApi.presentation.login.NewScreen
import com.nirali.sample.recipe.screeen.RecipeScreen

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Recipe.route)
    {
        composable(Routes.Login.route)
        {
            LoginField(navController)
        }
        composable(Routes.Home.route) {
            HomeScreen(navController)

        }
        composable(Routes.News.route) {
            NewScreen(innerPadding = innerPadding)
        }
        composable(Routes.Recipe.route) {
            RecipeScreen()
        }

    }
}
