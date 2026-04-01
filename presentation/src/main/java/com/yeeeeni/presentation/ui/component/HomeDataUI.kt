package com.yeeeeni.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.yeeeeni.data.local.entity.Diary
import com.yeeeeni.presentation.R
import com.yeeeeni.presentation.ui.common.CommonCard
import com.yeeeeni.presentation.ui.common.Gray200
import com.yeeeeni.presentation.ui.common.Gray400
import com.yeeeeni.presentation.ui.common.Gray600
import com.yeeeeni.presentation.ui.common.Pink300
import com.yeeeeni.presentation.ui.common.Pink400
import com.yeeeeni.presentation.ui.extension.clickableNoRipple
import com.yeeeeni.presentation.ui.extension.toKoreanDateFormat

@Composable
fun HomeDataUI(
    onDeleteAll: () -> Unit,
    onDelete: (Int) -> Unit,
    diaryList: List<Diary>,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp).clickableNoRipple(){
                onDeleteAll()
            },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(start = 2.dp),
                color = Gray400,
                text = "전체 삭제"
            )
        }
        LazyColumn(
            modifier = Modifier.background(Color.White)
        ) {
            items(diaryList) { diary ->
                CommonCard(
                    bottom = 5.dp,
                    borderColor = Pink300,
                    content = {
                        Column(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp)
                        ){
                            Row(
                                modifier = Modifier.fillMaxWidth().clickableNoRipple{
                                    if(diary.id == null) return@clickableNoRipple
                                    onDelete(diary.id!!)
                                },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    textAlign = TextAlign.Right,
                                    color = Gray400,
                                    text = diary.entryDate.toKoreanDateFormat()
                                )
                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = "delete",
                                    colorFilter = ColorFilter.tint(Pink400)
                                )
                            }
                            Text(
                                color = Gray600,
                                text = diary.title
                            )
                            Text(
                                color = Gray600,
                                text = diary.content,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                )
            }
        }
    }
}