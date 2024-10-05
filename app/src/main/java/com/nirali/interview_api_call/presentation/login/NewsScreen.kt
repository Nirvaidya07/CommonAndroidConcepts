package com.nirali.interview_api_call.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nirali.auth_design.presentation.screens.backgroundTask1
import com.nirali.interview_api_call.domain.model.Article
import com.nirali.interview_api_call.domain.model.NewsModel
import com.nirali.interview_api_call.presentation.login.viewmodel.NewsViewModel

@Preview
@Composable
fun NewScreen(innerPadding: PaddingValues,newsViewModel: NewsViewModel=hiltViewModel()) {
    val newsState by newsViewModel.newsState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        backgroundTask1()
        newsViewModel.getTrendingNews(NewsModel("us", "business", 1))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        newsState?.let { state ->
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error.isNotEmpty() -> {
                    Text(
                        text = "Error", modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp)
                    )
                }

                state.news.isNotEmpty() -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.news)
                        {
                            NewsItem(it)
                        }

                    }


                }
            }
        }


    }

}

@Composable
fun NewsItem(article: Article) {
    Row(
        Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            AsyncImage(
                model = "https://picsum.photos/200/300",
                contentDescription = article.title,
                modifier = Modifier
                    .wrapContentSize()
                    .size(150.dp),
                contentScale = ContentScale.Crop,
            )
        }

        Text(modifier = Modifier.padding(10.dp), text = "${article.title}")

    }
}