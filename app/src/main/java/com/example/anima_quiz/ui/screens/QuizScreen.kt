// QuizScreen.kt
package com.example.anima_quiz.ui

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
import com.example.anima_quiz.ui.components.*

@Composable
fun QuizScreen(
    questions: List<QuizQuestion>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var showScore by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

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
                    score = 0
                    showScore = false
                }
            )
        } else {

            if (currentQuestionIndex < questions.size && isCorrect == null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    QuizQuestionView(
                        image = questions[currentQuestionIndex].image,
                        question = questions[currentQuestionIndex].question
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    QuizButtons(
                        answers = questions[currentQuestionIndex].answers,
                        onAnswerSelected = { selectedAnswer ->
                            isCorrect = selectedAnswer == questions[currentQuestionIndex].correctAnswer
                            if (isCorrect == true) {
                                score += 1
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

