package com.nirali.expensetracker.core.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nirali.expensetracker.core.domain.model.ExpenseCategory
import com.nirali.expensetracker.core.presentation.home.viewmodel.HomeState
import com.nirali.expensetracker.core.presentation.home.viewmodel.HomeViewmodel

@Preview
@Composable
fun ExpenseTrackerModel(viewModel: HomeViewmodel, onClosed: () -> Unit = {}) {
    var expenseTitle by remember { mutableStateOf("") }
    var amount by remember { mutableDoubleStateOf(0.0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(
                brush =
                Brush.horizontalGradient(
                    colors = listOf(
                        Color.Black,
                        Color.Black.copy(alpha = 0.7f),
                        Color(0xFF42A974),

                        )
                ), shape = RoundedCornerShape(2.dp)
            )
            .padding(vertical = 10.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp))
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Add Expense", style = MaterialTheme.typography.headlineMedium)

                CustomTextField(value = expenseTitle, onValueChange = {
                    expenseTitle = it
                }, label = " Expense Title", placeholder = "Enter Expense Title")

                CustomTextField(
                    value = if (amount == 0.0) "" else amount.toString(),
                    onValueChange = {
                        amount = it.toDoubleOrNull() ?: 0.0
                    },
                    label = " Expense Amount"
                )

                ElevatedButton(
                    onClick = {
                        viewModel.addExpense(
                            ExpenseCategory(
                                title = expenseTitle,
                                expenseAmount = amount,
                                date = System.currentTimeMillis(),
                                expenseCategory = 0
                            )
                        )
                        onClosed()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 26.dp)
                ) {
                    Text(text = "Submit")
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String? = null

) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = label, color = MaterialTheme.colorScheme.primary)
        },
        placeholder = {
            Text(text = placeholder ?: "", color = Color.Gray)
        },
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFF42A974),
            focusedLabelColor = Color(0xFF42A974),
            containerColor = Color.Transparent,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color(0xFF42A974),
            errorLabelColor = Color.Red,
            disabledLabelColor = Color.Gray,
        ),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
    )

}