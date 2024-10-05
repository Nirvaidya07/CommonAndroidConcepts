/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :11:39 a.m.
 * Project Name :Interview
 *
 */
package com.nirali.expensetracker.core.data.repository

import com.nirali.expensetracker.core.data.room.dao.ExpenseDao
import com.nirali.expensetracker.core.domain.model.ExpenseCategory
import com.nirali.expensetracker.core.domain.repository.ExpenseCategoryRepository
import kotlinx.coroutines.flow.Flow

class ExpenseCategoryRepositoryImpl(private val expenseDao: ExpenseDao) :
    ExpenseCategoryRepository {

    override suspend fun insertExpenseCategory(expenseCategory: ExpenseCategory) {
        return expenseDao.insertExpense(expenseCategory)
    }

    override suspend fun deleteExpenseCategory(expenseCategory: ExpenseCategory) {
        return expenseDao.deleteExpense(expenseCategory)
    }

    override suspend fun getExpenseList(): Flow<List<ExpenseCategory>> {
        return expenseDao.getAllExpenses()
    }

    override suspend fun getExpenseCategoryById(id: Int): ExpenseCategory {
        return expenseDao.getExpenseById(id)
    }
}