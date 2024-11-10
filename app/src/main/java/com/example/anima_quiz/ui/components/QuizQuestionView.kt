package com.example.anima_quiz.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.anima_quiz.R

@Composable
fun QuizQuestionView(
    image: Int,
    question: String,
    tip: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .height(200.dp) // Set a fixed height
            .clip(RoundedCornerShape(16.dp)), // Round the corners
        contentScale = ContentScale.Crop // Crop the image to fill the bounds, avoiding white bars
    )

    Text(
        text = question,
        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
    )
    Text(
        text = "dica: $tip",
        style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp),
        textAlign = TextAlign.Center,
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f), shape = RoundedCornerShape(16.dp))
    )

}