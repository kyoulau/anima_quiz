package com.example.anima_quiz.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.anima_quiz.R
import com.example.anima_quiz.data.QuizQuestion
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.ui.components.QuizQuestionView

@Composable
fun QuizApp(navController: NavHostController, questions: List<Question>) {
    QuizScreen(questions = questions, navController = navController)
}