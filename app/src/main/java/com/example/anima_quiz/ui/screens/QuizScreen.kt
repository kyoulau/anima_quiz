// QuizScreen.kt
package com.example.anima_quiz.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            .background(Color.LightGray),
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally
                , verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Text(
                    text = if (isCorrect == true) "Correct!" else "Incorrect!",
                    color = if (isCorrect == true) Color.Green else Color.Red,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Button( onClick = {
                    isCorrect = null
                    currentQuestionIndex += 1
                    if (currentQuestionIndex >= questions.size) {
                        showScore = true
                    }},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White),
                    modifier = Modifier.height(75.dp),
                    shape = MaterialTheme.shapes.medium
                    ){
                    Text("Continuar")
                }
            }
        }



        LaunchedEffect(key1 = isCorrect) {
            kotlinx.coroutines.delay(5000) // 1-second delay
            isCorrect = null
            currentQuestionIndex += 1
            if (currentQuestionIndex >= questions.size) {
                showScore = true
            }
        }
    }
}

