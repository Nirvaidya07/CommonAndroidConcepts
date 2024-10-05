package com.nirali.expensetracker.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val title: String,
    val expenseAmount: Double,
    val date:Long,
    val expenseCategory: Int
)
