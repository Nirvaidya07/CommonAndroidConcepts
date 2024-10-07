/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :9:23 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.auth_design.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nirali.auth_design.domain.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao

}