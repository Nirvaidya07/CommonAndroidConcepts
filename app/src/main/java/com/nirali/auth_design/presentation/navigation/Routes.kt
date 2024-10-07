package com.nirali.auth_design.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val title: String? = null, val icon: ImageVector? = null) {

     object Login : Routes(route = "login_screen")
     object News : Routes(route = "news_screen")
     object Home : Routes(route = "home_screen",title = "Home", icon = Icons.Default.Home)
     object Search : Routes(route = "search_screen",title = "Search", icon = Icons.Default.Search)
     object Setting : Routes(route = "setting_screen",title = "Settings", icon = Icons.Default.Settings)
     object Recipe :Routes(route = "recipe_screen")
     object Registration : Routes(route = "registration_screen")
     object StateScreen : Routes(route = "state_screen")

}