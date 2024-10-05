package com.nirali.expensetracker.core.domain.repository

import com.nirali.expensetracker.core.domain.model.ExpenseCategory
import kotlinx.coroutines.flow.Flow

interface ExpenseCategoryRepository {

    suspend fun insertExpenseCategory(expenseCategory: ExpenseCategory)

    suspend fun deleteExpenseCategory(expenseCategory: ExpenseCategory)

    suspend fun getExpenseList(): Flow<List<ExpenseCategory>>

    suspend fun getExpenseCategoryById(id: Int): ExpenseCategory

}