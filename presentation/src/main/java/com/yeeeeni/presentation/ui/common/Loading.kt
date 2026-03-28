package com.yeeeeni.presentation.ui.common


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotsLoading (
    color: Color = Pink400,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DotsLoadingIndicator(
            color = color,
            dotSize = 10.dp,
            spacing = 8.dp
        )
    }
}

@Composable
fun DotsLoadingIndicator(
    color: Color = Primary,
    dotSize: Dp = 8.dp,
    spacing: Dp = 6.dp
) {
    val dotCount = 3
    val infiniteTransition = rememberInfiniteTransition(label = "dots")

    val alphas = (0 until dotCount).map { index ->
        infiniteTransition.animateFloat(
            initialValue = 0.3f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1000
                    0.3f at 0
                    1f at 300
                    0.3f at 600
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(index * 200)
            ),
            label = "alpha_$index"
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        alphas.forEach { alpha ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(color.copy(alpha = alpha.value))
            )
        }
    }
}