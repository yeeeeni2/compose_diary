package com.yeeeeni.presentation.ui.common

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

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