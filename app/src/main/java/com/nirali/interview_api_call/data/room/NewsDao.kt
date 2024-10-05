/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :5:11 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.interview_api_call.data.room

import androidx.room.Dao
import androidx.room.Insert
import com.nirali.interview_api_call.domain.model.Article
import com.nirali.interview_api_call.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(news: Article)

    @androidx.room.Query("SELECT * FROM  news_articles")
    fun getNews(): Flow<List<Article>>


}