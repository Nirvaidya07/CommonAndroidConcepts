/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :2:29 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample.recipe.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirali.sample.recipe.domain.model.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {


    @Query("SELECT * FROM dog_table")
   suspend fun getAllBeads(): List<Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBread(breeds: List<Data>)


}