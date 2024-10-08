/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :5:13 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nirali.restApi.data.room.converters.SourceConverter
import com.nirali.restApi.domain.model.Article


@TypeConverters(SourceConverter::class)
@Database(entities = [Article::class], version = 1, exportSchema = false)
 abstract  class NewsDatabase : RoomDatabase() {
     abstract fun newsDao(): NewsDao
 }