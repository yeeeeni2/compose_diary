package com.yeeeeni.presentation.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yeeeeni.presentation.ui.common.FloatingButton

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        // 메인 콘텐츠
        LazyColumn {
            items(5) { index ->
                Text(text = "글입니다")
            }
        }

        // 글 작성하기
        FloatingButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                navController.navigate(Screen.Diary.route)
            },
            imageVector = Icons.Default.Create,
            contentDescription = "Write",
        )
    }
}