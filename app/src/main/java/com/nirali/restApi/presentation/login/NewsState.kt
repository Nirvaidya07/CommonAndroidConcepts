package com.nirali.restApi.presentation.login

import com.nirali.restApi.domain.model.Article

data class NewsState(
    val isLoading: Boolean = false,
    val news: List<Article> = emptyList(),
    val error: String = "",
)