package com.example.composables

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.common.R

@Composable
fun CircleImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Drink Image",
        placeholder = painterResource(id = R.drawable.ic_gear),
        error = painterResource(id = R.drawable.ic_cancel),
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
    )
}