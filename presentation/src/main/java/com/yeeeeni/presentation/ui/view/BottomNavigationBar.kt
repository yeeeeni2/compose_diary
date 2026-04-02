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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yeeeeni.presentation.ui.common.Pink200
import com.yeeeeni.presentation.ui.common.Pink300
import com.yeeeeni.presentation.ui.common.Primary


@Composable
// 바텀 네비게이션바
fun BottomNavigationBar(
    navController: NavController
) {
    val navigationItems = listOf(
        NavigationItem(
            title = "홈",
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        NavigationItem(
            title = "달력",
            icon = Icons.Default.Person,
            route = Screen.Calendar.route
        ),
        NavigationItem(
            title = "설정",
            icon = Icons.Default.Settings,
            route = Screen.Setting.route
        )
    )

    // 현재 route를 기반으로 선택 index 계산
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val selectedNavigationIndex = navigationItems.indexOfFirst { it.route == currentRoute }

    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                // route 기반
                selected = selectedNavigationIndex == index,
                onClick = {
                    navController.navigate(item.route) {
                        // 백스택 중복 쌓임 방지
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex)
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



