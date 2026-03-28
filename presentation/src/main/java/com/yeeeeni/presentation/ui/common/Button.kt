package com.yeeeeni.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FloatingButton(
    onClick: () -> Unit,
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
) {
    FloatingActionButton(
        onClick,
        modifier = modifier,
        shape = CircleShape,
        containerColor = Primary,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription, tint = Color.White)
    }
}


@Composable
fun CommonButton(
    onClick: () -> Unit,
    title: String,
    ///null이면 전체 너비
    width: Dp? = null,
    enable: Boolean = true,
    enableColor: Color = Pink400,
    disableColor: Color = Primary,
    textColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (!enable) return@clickable
                onClick()
            }
            .then(
                if (width != null) Modifier.width(width)
                else Modifier.fillMaxWidth()
            )
            .clip(RoundedCornerShape(999.dp))
            .background(if (enable) enableColor else disableColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = title,
            textAlign = TextAlign.Center,
            style = TextStyle(color = textColor),
            fontSize = 16.sp,
        )
    }
}