/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :2:42 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nirali.sample.recipe.domain.model.Data

@TypeConverters(DogConverters::class, RelationConverters::class)
@Database(
    entities = [Data::class],
    version = 1,
    exportSchema = false
)
abstract class DogDatabase : RoomDatabase() {
    abstract val dogDao: DogDao
}