/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :5:11 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.data.room

import androidx.room.Dao
import androidx.room.Insert
import com.nirali.restApi.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert
    suspend fun insertNews(news: Article)

    @androidx.room.Query("SELECT * FROM  news_articles")
    fun getNews(): Flow<List<Article>>


}