package com.yeeeeni.diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.yeeeeni.diary.ui.theme.DiaryTheme
import com.yeeeeni.presentation.ui.view.BottomNavigationBar
import com.yeeeeni.presentation.ui.view.CalendarScreen
import com.yeeeeni.presentation.ui.view.DiaryScreen
import com.yeeeeni.presentation.ui.view.HomeScreen
import com.yeeeeni.presentation.ui.view.Screen
import com.yeeeeni.presentation.ui.view.SettingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiaryTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val bottomNavRoutes = setOf(
        Screen.Home.route,
        Screen.Calendar.route,
        Screen.Setting.route,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            ///바텀바에 속한 경우에만 바텀바 보이기
            if (currentRoute in bottomNavRoutes) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        val graph = navController.createGraph(startDestination = Screen.Home.route) {
            composable(route = Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(route = Screen.Calendar.route) {
                CalendarScreen()
            }
            composable(route = Screen.Setting.route) {
                SettingScreen()
            }
            composable(route = Screen.Diary.route) {
                DiaryScreen(navController)
            }
        }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    DiaryTheme {
        MainScreen()
    }
}