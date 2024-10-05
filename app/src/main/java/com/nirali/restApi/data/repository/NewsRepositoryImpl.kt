/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :4:26 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.data.repository

import com.nirali.restApi.data.remote.ApiService
import com.nirali.restApi.data.room.NewsDao
import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.domain.model.NewsResponse
import com.nirali.restApi.domain.repository.NewsRepository
import com.nirali.restApi.domain.utils.Resource
import com.nirali.restApi.domain.utils.safeApiCall
import com.nirali.restApi.presentation.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val api: ApiService, private val newsDao: NewsDao) :
    NewsRepository {

    override suspend fun getTrendingNews(
        newsModel: NewsModel
    ): Resource<NewsResponse> {
        return safeApiCall {
            api.getTrendingListAnime(
                newsModel.country,
                newsModel.category,
                API_KEY
            )
        }

    }

    override suspend fun saveNews(article: Article) {
        return newsDao.insertNews(article)
    }

    override fun getLocalNews(): Flow<List<Article>> {
        return newsDao.getNews()
    }
}