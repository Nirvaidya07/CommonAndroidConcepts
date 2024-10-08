/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :4:44 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.presentation.login.viewmodel

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirali.restApi.data.remote.NewsPagingSource
import com.nirali.restApi.data.remote.RetrofitInstance
import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.domain.repository.NewsRepository
import com.nirali.restApi.domain.utils.Resource
import com.nirali.restApi.presentation.login.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
        //getLocalNews()
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

    fun getpagingNews(newsModel: NewsModel): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 50, prefetchDistance = 50, enablePlaceholders = false),
            pagingSourceFactory = { NewsPagingSource(repository, newsModel.country, newsModel.category) }
        ).flow
            .cachedIn(viewModelScope)
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
