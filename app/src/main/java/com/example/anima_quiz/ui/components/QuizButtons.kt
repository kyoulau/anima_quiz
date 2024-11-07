package com.example.anima_quiz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun QuizButtons(
    answers: List<String>,
    onAnswerSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Define the fraction of screen width for spacing (e.g., 2%)
    val spacingFraction = 0.03f
    val spacing = screenWidth * spacingFraction

    Column(modifier = modifier) {
        for (i in answers.indices step 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = spacing/2)
                    .background(color = Color.White),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // First button in the row
                QuizButton(
                    text = answers[i],
                    onClick = { onAnswerSelected(answers[i]) },
                    modifier = Modifier.weight(1f)
                )

                // Second button in the row, if exists
                if (i + 1 < answers.size) {
                    QuizButton(
                        text = answers[i + 1],
                        onClick = { onAnswerSelected(answers[i + 1]) },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f)) // Empty space to balance the row
                }
            }
        }
    }
}