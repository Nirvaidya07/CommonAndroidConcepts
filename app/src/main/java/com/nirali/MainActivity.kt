package com.nirali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nirali.auth_design.presentation.navigation.AppNavigation
import com.nirali.restApi.ui.theme.InterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //val repository: NewsRepository = NewsRepositoryImpl(RetrofitInstance.api)

        // Create the ViewModelFactory
       // val factory = NewsViewModelFactory(repository)

        // Initialize the ViewModel using the factory
       // val viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        setContent {
            InterviewTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    NewScreen(newsViewModel =viewModel,innerPadding=innerPadding)
                    AppNavigation( innerPadding)
                }
            }
        }
    }
}

