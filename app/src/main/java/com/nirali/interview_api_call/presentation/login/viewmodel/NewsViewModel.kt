/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :4:44 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.interview_api_call.presentation.login.viewmodel

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirali.interview_api_call.domain.model.Article
import com.nirali.interview_api_call.domain.model.NewsModel
import com.nirali.interview_api_call.domain.repository.NewsRepository
import com.nirali.interview_api_call.domain.utils.Resource
import com.nirali.interview_api_call.presentation.login.NewsState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val repository: NewsRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow(NewsState())
    val newsState: StateFlow<NewsState?> = _newsState.asStateFlow()

    init {
        getLocalNews()
    }

    fun getTrendingNews(newsModel: NewsModel) {
        viewModelScope.launch {
            _newsState.value = _newsState.value.copy(isLoading = true)
            if (!isNetworkAvailable()) {
                getLocalNews()
            } else {
                when (val result = repository.getTrendingNews(newsModel)) {
                    is Resource.Success -> {
                        // Save news to local database
                        result.data?.articles?.forEach {
                            saveNews(it)
                        }

                        _newsState.value = result.data.let {
                            _newsState.value.copy(
                                isLoading = false,
                                news = it?.articles ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _newsState.value = _newsState.value.copy(
                            isLoading = false,
                            error = result.message ?: "An unknown error occurred"
                        )
                    }

                    is Resource.Loading -> {}

                }
            }

        }
    }

    fun saveNews(article: Article) {
        viewModelScope.launch {
            repository.saveNews(article)

        }
    }

    private fun getLocalNews() {
        viewModelScope.launch {
            repository.getLocalNews().collect { news ->
                _newsState.value = _newsState.value.copy(
                    news = news,
                    isLoading = false,

                    )
            }


        }
    }

    @SuppressLint("MissingPermission")
    private fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

}
