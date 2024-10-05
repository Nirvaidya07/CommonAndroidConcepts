package com.nirali.interview_api_call.presentation.login.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirali.interview_api_call.domain.repository.NewsRepository
import com.nirali.interview_api_call.presentation.login.viewmodel.NewsViewModel

//class NewsViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
//            return NewsViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}