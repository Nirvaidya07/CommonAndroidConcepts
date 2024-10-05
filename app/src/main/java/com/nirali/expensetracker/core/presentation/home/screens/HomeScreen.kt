package com.nirali.expensetracker.core.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nirali.auth_design.presentation.navigation.Routes
import com.nirali.expensetracker.core.domain.model.ExpenseCategory
import com.nirali.expensetracker.core.presentation.home.viewmodel.HomeViewmodel


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewmodel = hiltViewModel()) {
    HomeScreenState(viewModel, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenState(viewModel: HomeViewmodel, navController: NavController) {
    val state = viewModel.homeState.collectAsStateWithLifecycle()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show() // Trigger showing the bottom sheet
        } else {
            sheetState.hide() // Trigger hiding the bottom sheet
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getExpenseList()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Expense Tracker")
        })
    },
        bottomBar = {
            NavigationBar {
                listOf(
                    Routes.Home,
                    Routes.Search,
                    Routes.Setting
                ).forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        icon = {
                            item.icon?.let {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            }
                        },
                        label = {
                            item.title?.let {
                                Text(text = item.title)
                            }
                        },
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        }

                    )

                }
            }

        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.TopStart),  // Align horizontally to center
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                showBottomSheet = true
                                //viewModel.addExpense()
                            }, modifier = Modifier
                                .padding(12.dp)
                                .wrapContentSize()
                        ) {
                            Text(text = "Add Expense")

                        }
                        Button(
                            onClick = {
                                // viewModel.addIncome()
                            }, modifier = Modifier
                                .padding(12.dp)
                                .wrapContentSize()
                        ) {
                            Text(text = "Add Income")

                        }

                    }
                }



                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .background(Color(0xFF42A974)), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Total Expense",
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterStart),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            )
                        )
                    }

                }
                items(state.value.expenseList)
                {
                    ExpenseItem(it)
                }

            }

        }

    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxHeight(),
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        )
        {
            ExpenseTrackerModel(viewModel, onClosed =
            {
                showBottomSheet = false
            })
        }
    }

}

@Composable
fun ExpenseItem(expenseCategory: ExpenseCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Text(
                text = expenseCategory.title,
                Modifier.wrapContentSize(),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${expenseCategory.expenseAmount}",
                Modifier.wrapContentSize(),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal)
            )


        }
    }
}




