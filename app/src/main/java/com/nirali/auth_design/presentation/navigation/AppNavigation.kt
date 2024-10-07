package com.nirali.auth_design.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nirali.auth_design.presentation.screens.LoginField
import com.nirali.auth_design.presentation.screens.RegistrationScreen
import com.nirali.auth_design.presentation.screens.StateScreen
import com.nirali.auth_design.presentation.viewmodel.AuthViewModel
import com.nirali.expensetracker.core.presentation.home.screens.HomeScreen
import com.nirali.restApi.presentation.login.NewScreen
import com.nirali.sample.recipe.screeen.RecipeScreen
import com.nirali.sample.recipe.viewmodel.DogViewmodel

@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route)
    {
        composable(Routes.Login.route)
        {
            LoginField {
                navController.navigate(Routes.Home.route)
            }
        }
        composable(Routes.Home.route) {
            HomeScreen(navController)

        }
        composable(Routes.News.route) {
            NewScreen(innerPadding = innerPadding)
        }
        composable(Routes.Recipe.route) {
            val dogViewmodel: DogViewmodel = hiltViewModel()
            val dogState = dogViewmodel.dogState.collectAsStateWithLifecycle()
            val isRefreshing by dogViewmodel.isRefreshing.collectAsState()
            RecipeScreen(
                dogState, isRefreshing,
                onRefresh = dogViewmodel::refresh
            )
        }

        composable(Routes.Registration.route)
        {
            val authViewModel: AuthViewModel = hiltViewModel()
            RegistrationScreen(onSubmit = { userState ->
                authViewModel.login(
                    userState.email,
                    userState.phone,
                    userState.username,
                    userState.password,

                    ) {
                    navController.navigate(Routes.Login.route)
                }

            })
        }
        composable(Routes.StateScreen.route)
        {
            StateScreen()
        }

    }
}
