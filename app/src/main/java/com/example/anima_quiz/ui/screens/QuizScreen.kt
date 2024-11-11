package com.example.anima_quiz.ui.screens

import android.icu.text.DecimalFormat
import android.media.AudioManager
import android.media.MediaPlayer
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
import com.example.anima_quiz.R
import com.example.anima_quiz.data.QuizQuestion
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.ui.components.*
import java.math.RoundingMode

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.anima_quiz.feature.data.viewModel.PlayerViewModel


@Composable
fun QuizScreen(
    playerViewModel: PlayerViewModel,
    questions: List<Question>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(true) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf<Float>(0.0f) }
    var showScore by remember { mutableStateOf(false) }
    var showLeaderboard by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }
    val timeLimit = 15.0f
    var timeRemaining by remember { mutableStateOf<Float>(timeLimit) }

    val onTimeUp = {
        isCorrect = false
    }

    val onTimeRemaining: (Float) -> Unit = { remaining ->
        timeRemaining = remaining
    }

    fun playCorrectSound(context: Context) {
        try {
            val mediaPlayer = MediaPlayer.create(context, R.raw.correct_sound)
            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                mediaPlayer.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun playWrongSound(context: Context) {
        try {
            val mediaPlayer = MediaPlayer.create(context, R.raw.wrong_sound)
            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                mediaPlayer.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1000)
        isLoading = false
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 4.dp
            )
        }
    }
    else{
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            if (showScore) {
                QuizCompleted(
                    playerViewModel= playerViewModel,
                    nav = navController,
                    score = score,
                    total = questions.size,
                    onContinue = {
                        showScore = false
                        showLeaderboard = true
                    }
                )
            }
            else if (showLeaderboard){
                LeaderboardScreen(
                    playerViewModel = playerViewModel,
                    onRestart = {
                    currentQuestionIndex = 0
                    score = 0.0f
                    showScore = false
                    showLeaderboard = false
                    },
                    sair = {navController.navigate("welcome")}
                )
            }
            else {

                if (currentQuestionIndex < questions.size && isCorrect == null) {

                    questions[currentQuestionIndex].randomizeOptions()
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        QuestionTimer(
                            timeLimit,
                            onTimeUp = onTimeUp,
                            onTimeRemaining = onTimeRemaining
                        )
                        QuizQuestionView(
                            image = questions[currentQuestionIndex].imageUrl,
                            question = questions[currentQuestionIndex].questionText,
                            tip = questions[currentQuestionIndex].tips.random(),
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        QuizButtons(
                            answers = questions[currentQuestionIndex].options,
                            onAnswerSelected = { selectedAnswer ->
                                isCorrect =
                                    questions[currentQuestionIndex].options.indexOf(selectedAnswer) == questions[currentQuestionIndex].correctAnswerIndex
                                if (isCorrect == true) {
                                    score += (timeRemaining / timeLimit.toFloat())
                                    playCorrectSound(context)
                                }
                                else{
                                    playWrongSound(context)
                                }
                            }
                        )
                    }
                }
            }
        }
    }


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
                    color = if (isCorrect == true) Color(0xFF4CAF50) else Color(0xFFF44336),
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
            kotlinx.coroutines.delay(3000)
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
    timeLimit: Float,
    onTimeUp: () -> Unit,
    onTimeRemaining: (Float) -> Unit
) {
    val timeLimitMillis = (timeLimit * 1000).toInt()
    var timeRemainingMillis by remember { mutableStateOf(timeLimitMillis) }

    LaunchedEffect(timeRemainingMillis) {
        if (timeRemainingMillis > 0) {
            kotlinx.coroutines.delay(100L)
            timeRemainingMillis -= 100
            onTimeRemaining(timeRemainingMillis / 1000f)
        } else {
            onTimeUp()
        }
    }

    Text(
        text = "${timeRemainingMillis / 1000f}",
        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 1f)),
        modifier = Modifier.padding(8.dp)
    )
}
