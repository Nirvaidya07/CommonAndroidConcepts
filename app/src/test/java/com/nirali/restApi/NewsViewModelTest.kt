/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :9:22 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import app.cash.turbine.test
import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.domain.model.NewsResponse
import com.nirali.restApi.domain.repository.NewsRepository
import com.nirali.restApi.domain.utils.Resource
import com.nirali.restApi.presentation.login.viewmodel.NewsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var repository: NewsRepository

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = NewsViewModel(connectivityManager, repository)
    }

    @Test
    fun `getTrendingNews should update newsState with success data`() = runTest {
        // Arrange
        val networkCapabilities = mock(NetworkCapabilities::class.java)
        Mockito.`when`(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork))
            .thenReturn(networkCapabilities)
        Mockito.`when`(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            .thenReturn(true)

        val remoteNews = listOf(
            Article(
                1,
                "News1",
                "Author1",
                "Description1",
                "Date1",
                null,
                "Title1",
                "url1",
                "urlToImage1"
            ),
            Article(
                2,
                "News2",
                "Author2",
                "Description2",
                "Date2",
                null,
                "Title2",
                "url2",
                "urlToImage2"
            )
        )

        // Mock the repository to return a successful response
        Mockito.`when`(repository.getTrendingNews(any())).thenReturn(
            Resource.Success(
                NewsResponse(remoteNews.toMutableList(), "ok", remoteNews.size)
            )
        )

        // Act and Assert using Turbine for flow testing
        viewModel.newsState.test {
            // Trigger the ViewModel method
            viewModel.getTrendingNews(NewsModel("us", "", 1))

            // Assert that the state has loading = true initially
            assert(awaitItem()?.isLoading == true)

            // Assert the next state where the news is loaded successfully
            val state = awaitItem()
            assert(state?.news == remoteNews)
            assert(state?.isLoading == false)
            assert(state?.error == null)
        }
    }
}