package com.yeeeeni.presentation.ui.view

import android.R.attr.text
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldDecorator
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.yeeeeni.presentation.ui.common.CommonButton
import com.yeeeeni.presentation.ui.common.Gray200
import com.yeeeeni.presentation.ui.common.Pink400
import com.yeeeeni.presentation.ui.common.Primary
import com.yeeeeni.presentation.ui.viewModel.DiaryViewModel

@Composable
fun DiaryScreen(
    navController: NavController,
) {
    val viewModel: DiaryViewModel = hiltViewModel()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BackHandler {
        navController.popBackStack()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding()
    ) {
        // 뒤로가기
        Row {
            Image(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    navController.popBackStack()
                },
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back",
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "오늘 뭐하셨어요?",
                fontSize = 16.sp,
            )
        }

        // 제목
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Gray200, RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            BasicTextField(
                state = viewModel.titleState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
                    .focusRequester(focusRequester),
                lineLimits = TextFieldLineLimits.SingleLine,
                decorator = TextFieldDecorator { innerTextField ->
                    if (viewModel.titleState.text.isEmpty()) {
                        Text(
                            modifier = Modifier.paddingFromBaseline(top = 0.dp),
                            text = "제목",
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                    }
                    innerTextField()
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            )
        }

        // 내용
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Gray200, RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            BasicTextField(
                state = viewModel.contentState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp),
                decorator = TextFieldDecorator { innerTextField ->
                    if (viewModel.contentState.text.isEmpty()) {
                        Text(
                            modifier = Modifier.paddingFromBaseline(top = 0.dp),
                            text = "오늘 하루를 기록해보세요...",
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                    }
                    innerTextField()
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            )
        }

        // 저장하기
        CommonButton(
            onClick = {
                viewModel.insert()
                navController.popBackStack()
            },
            title = "저장하기",
            enable = viewModel.isAllTextEmpty(),
        )
    }
}