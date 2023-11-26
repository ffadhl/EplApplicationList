package com.fadhlalhafizh.submissionjetpackcompose.ui.assetitem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun EmptyListItem(
    alertMessage: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = alertMessage,
            color = textColor
        )
    }
}