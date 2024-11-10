// QuizScreen.kt
package com.example.anima_quiz.ui

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anima_quiz.data.QuizQuestion
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.ui.components.*
import java.math.RoundingMode

@Composable
fun QuizScreen(
    questions: List<Question>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf<Float>(0.0f) }
    var showScore by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    val timeLimit = 15.0f // 15 seconds per question
    var timeRemaining by remember { mutableStateOf<Float>(timeLimit) }

    val onTimeUp = {
        isCorrect = false // Treat as incorrect if time runs out
    }

    val onTimeRemaining: (Float) -> Unit = { remaining ->
        timeRemaining = remaining
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        if (showScore) {
            QuizCompleted(
                score = score,
                total = questions.size,
                onRestart = {
                    currentQuestionIndex = 0
                    score = 0.0f
                    showScore = false
                }
            )
        } else {

            if (currentQuestionIndex < questions.size && isCorrect == null) {

                questions[currentQuestionIndex].randomizeOptions()
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    QuestionTimer(timeLimit, onTimeUp = onTimeUp, onTimeRemaining = onTimeRemaining)
                    QuizQuestionView(
                        image = questions[currentQuestionIndex].imageUrl,
                        question = questions[currentQuestionIndex].questionText,
                        tip = questions[currentQuestionIndex].tips.random(),
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    QuizButtons(
                        answers = questions[currentQuestionIndex].options,
                        onAnswerSelected = { selectedAnswer ->
                            isCorrect = questions[currentQuestionIndex].options.indexOf(selectedAnswer) == questions[currentQuestionIndex].correctAnswerIndex
                            if (isCorrect == true) {
                                score += (timeRemaining / timeLimit.toFloat())

                            }
                            // No LaunchedEffect here
                        }
                    )
                }
            }
        }
    }

    // LaunchedEffect outside the lambda, observing changes to isCorrect
    if (isCorrect != null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(32.dp)
            ) {
                Text(
                    text = if (isCorrect == true) "Correct!" else "Incorrect!",
                    color = if (isCorrect == true) Color(0xFF4CAF50) else Color(0xFFF44336), // Green and red tones for feedback
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Button(
                    onClick = {
                        isCorrect = null
                        currentQuestionIndex += 1
                        if (currentQuestionIndex >= questions.size) {
                            showScore = true
                        }
                    },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(60.dp)
                        .width(180.dp)
                ) {
                    Text("Next", fontWeight = FontWeight.Bold)
                }
            }
        }

        LaunchedEffect(key1 = isCorrect) {
            kotlinx.coroutines.delay(3000) // 3-second delay for feedback display
            isCorrect = null
            currentQuestionIndex += 1
            if (currentQuestionIndex >= questions.size) {
                showScore = true
            }
        }
    }

}
@Composable
fun QuestionTimer(
    timeLimit: Float, // in seconds
    onTimeUp: () -> Unit,
    onTimeRemaining: (Float) -> Unit
) {
    val timeLimitMillis = (timeLimit * 1000).toInt() // Convert time limit to milliseconds
    var timeRemainingMillis by remember { mutableStateOf(timeLimitMillis) }

    LaunchedEffect(timeRemainingMillis) {
        if (timeRemainingMillis > 0) {
            kotlinx.coroutines.delay(100L) // wait for 0.2 second (200 milliseconds)
            timeRemainingMillis -= 100 // reduce time by 200 milliseconds
            onTimeRemaining(timeRemainingMillis / 1000f) // convert to seconds for onTimeRemaining
        } else {
            onTimeUp() // Trigger when the time is up
        }
    }

    Text(
        text = "${timeRemainingMillis / 1000f}",
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 1f)),
        modifier = Modifier.padding(8.dp)
    )
}
