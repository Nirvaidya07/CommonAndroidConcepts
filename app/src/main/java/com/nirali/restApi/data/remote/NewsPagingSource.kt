/**Created By Nirali Pandya
 * Date :2024-10-06
 * Time :2:57 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.restApi.data.remote


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nirali.restApi.domain.model.Article
import com.nirali.restApi.domain.model.NewsModel
import com.nirali.restApi.domain.repository.NewsRepository

class NewsPagingSource(
    private val repository: NewsRepository,
    private val country: String,
    private val category: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {

            val currentPage = params.key ?: 1
            val response = repository.getTrendingNews(NewsModel(country, category, currentPage))
            val articles = response.data?.articles ?: emptyList()
            LoadResult.Page(
                data = articles,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (articles.isEmpty()) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}