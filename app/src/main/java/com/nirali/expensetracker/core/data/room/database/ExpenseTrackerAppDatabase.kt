/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :11:48 a.m.
 * Project Name :Interview
 *
 */
package com.nirali.expensetracker.core.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nirali.expensetracker.core.data.room.dao.ExpenseDao
import com.nirali.expensetracker.core.domain.model.ExpenseCategory


@Database(
    entities = [ExpenseCategory::class],
    version = 1,
    exportSchema = true
)
abstract class ExpenseTrackerAppDatabase : RoomDatabase() {

    abstract val expenseDao: ExpenseDao

}