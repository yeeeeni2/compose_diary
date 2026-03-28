package com.yeeeeni.presentation.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.BrowserNotSupported
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.yeeeeni.presentation.ui.common.FloatingButton
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yeeeeni.presentation.ui.common.CommonButton
import com.yeeeeni.presentation.ui.common.DotsLoading
import com.yeeeeni.presentation.ui.common.Gray100
import com.yeeeeni.presentation.ui.common.Gray200
import com.yeeeeni.presentation.ui.common.Gray300
import com.yeeeeni.presentation.ui.common.Gray400
import com.yeeeeni.presentation.ui.common.Gray600
import com.yeeeeni.presentation.ui.viewModel.DiaryViewModel
import com.yeeeeni.presentation.ui.viewState.UiState
import java.time.format.TextStyle

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: DiaryViewModel = hiltViewModel()
) {
    val diaryListState by viewModel.diaryListState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        when (diaryListState) {
            is UiState.Loading -> DotsLoading()
            is UiState.Empty -> EmptyComponent(
                "작성된 일기가 없어요"
            )
            is UiState.Error -> ErrorComponent(
                onClick = {
                    viewModel.retry()
                },
            )
            is UiState.Success -> {
                val list = (diaryListState as UiState.Success).data
                LazyColumn {
                    items(list) { diary ->
                        Text(text = diary.title)
                    }
                }
            }
        }

        FloatingButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = { navController.navigate(Screen.Diary.route) },
            imageVector = Icons.Default.Create,
            contentDescription = "Write",
        )
    }
}

@Composable
fun EmptyComponent(
    content: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.Filter, contentDescription = "empty", tint = Gray200)
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = content,
            color = Gray600
        )
    }
}

@Composable
fun ErrorComponent(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.BrowserNotSupported, contentDescription = "error", tint = Gray200)
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "잠시 오류가 발생했어요. 다시 시도해주세요",
            color = Gray600
        )
        CommonButton(
            onClick = {
                onClick()
            },
            width = 110.dp,
            title = "다시시도",
            enableColor = Gray100,
            textColor = Gray400,
        )
    }
}