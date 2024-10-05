package com.nirali.interview_api_call

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.nirali.auth_design.presentation.navigation.AppNavigation
import com.nirali.interview_api_call.data.remote.RetrofitInstance
import com.nirali.interview_api_call.data.repository.NewsRepositoryImpl
import com.nirali.interview_api_call.domain.repository.NewsRepository
import com.nirali.interview_api_call.presentation.login.NewScreen
import com.nirali.interview_api_call.presentation.login.viewmodel.NewsViewModel
import com.nirali.interview_api_call.ui.theme.InterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

