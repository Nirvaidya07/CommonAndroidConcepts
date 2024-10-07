package com.nirali.restApi.presentation.login

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
import androidx.compose.material3.Button
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.nirali.auth_design.presentation.screens.backgroundTask1
import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.presentation.login.viewmodel.NewsViewModel

@Preview
@Composable
fun NewScreen(innerPadding: PaddingValues, newsViewModel: NewsViewModel = hiltViewModel()) {
//    val newsState by newsViewModel.newsState.collectAsState()
    val newsItems =
        newsViewModel.getpagingNews(NewsModel("us", "business", 1)).collectAsLazyPagingItems()
    LaunchedEffect(key1 = Unit) {
        // backgroundTask1()
        //newsViewModel.getpagingNews(NewsModel("us", "business", 1))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {

        when (newsItems.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

//            LoadState.Error -> {
//                Text(
//                    text = "Error", modifier = Modifier
//                        .align(Alignment.Center)
//                        .padding(10.dp)
//                )
//            }

            else ->
//                    {newsItems.a.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(newsItems.itemCount)
                    { index ->
                        val article = newsItems[index]
                        if (article != null) {
                            //NewsItem(article)
                        }
                    }

                    //  }

                    // Check if we can load more items
                    if (newsItems.loadState.append is LoadState.Loading) {
                        item {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    } else if (newsItems.loadState.append is LoadState.Error) {
                        item {
                            Text(
                                text = "Error loading more news",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    // Custom "Load More" Button (if needed)
                    if (newsItems.loadState.append.endOfPaginationReached) {
                        item {
                            Text(
                                "No more items to load",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    } else {
                        item {
                            Button(onClick = { newsItems.retry() }) {
                                Text("Load More")
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