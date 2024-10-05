/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :4:27 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.domain.repository

import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.domain.model.NewsResponse
import com.nirali.restApi.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getTrendingNews(newsModel: NewsModel): Resource<NewsResponse>

    suspend fun saveNews(article: Article)

    fun getLocalNews(): Flow<List<Article>>
}