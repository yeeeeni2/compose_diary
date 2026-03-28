package com.yeeeeni.presentation.ui.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.yeeeeni.presentation.ui.common.Pink200
import com.yeeeeni.presentation.ui.common.Pink300
import com.yeeeeni.presentation.ui.common.Primary


@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon =Icons.Default.Home,
            route = Screen.Home.route
        ),
        NavigationItem(
            title = "Calendar",
            icon = Icons.Default.Person,
            route = Screen.Calendar.route
        ),
        NavigationItem(
            title = "Setting",
            icon = Icons.Default.Settings,
            route = Screen.Setting.route
        )
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Primary
                        else Pink300
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Pink200,
                    unselectedIconColor = Pink300,
                    indicatorColor = Primary,
                )

            )
        }
    }
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Calendar: Screen("calendar_screen")
    object Setting: Screen("setting_screen")
    object Diary: Screen("diary_screen")
}



