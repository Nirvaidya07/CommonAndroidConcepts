/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :4:26 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.interview_api_call.data.repository

import com.nirali.interview_api_call.data.remote.ApiService
import com.nirali.interview_api_call.data.room.NewsDao
import com.nirali.interview_api_call.domain.model.Article
import com.nirali.interview_api_call.domain.model.NewsModel
import com.nirali.interview_api_call.domain.model.NewsResponse
import com.nirali.interview_api_call.domain.repository.NewsRepository
import com.nirali.interview_api_call.domain.utils.Resource
import com.nirali.interview_api_call.domain.utils.safeApiCall
import com.nirali.interview_api_call.presentation.utils.Constants.Companion.API_KEY
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