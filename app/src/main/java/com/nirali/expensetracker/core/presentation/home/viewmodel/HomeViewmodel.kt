/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :12:03 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.expensetracker.core.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirali.expensetracker.core.domain.model.ExpenseCategory
import com.nirali.expensetracker.core.domain.repository.ExpenseCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val expenseList: List<ExpenseCategory> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)


@HiltViewModel
class HomeViewmodel @Inject constructor(private val repository: ExpenseCategoryRepository):ViewModel(){

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun addExpense(expenseCategory: ExpenseCategory) {
        viewModelScope.launch {
            repository.insertExpenseCategory(expenseCategory)
        }

    }

    fun deleteExpense(expenseCategory: ExpenseCategory) {
        viewModelScope.launch {
            repository.deleteExpenseCategory(expenseCategory)
        }
    }
    fun getExpenseList() {
        viewModelScope.launch {
            _homeState.value = _homeState.value.copy(isLoading = true)
            try {
               repository.getExpenseList()
                    .collect{ expenseList ->
                        _homeState.value = _homeState.value.copy(
                            expenseList = expenseList,
                            isLoading = false,
                            error=null

                        )

                    }

            }catch (e:Exception){
                _homeState.value = _homeState.value.copy(
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                )

            }

        }
    }



}