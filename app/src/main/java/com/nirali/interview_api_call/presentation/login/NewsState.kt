package com.nirali.interview_api_call.presentation.login

import com.nirali.interview_api_call.domain.model.Article

data class NewsState(
    val isLoading: Boolean = false,
    val news: List<Article> = emptyList(),
    val error: String = "",
)