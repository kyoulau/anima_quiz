package com.example.anima_quiz.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.ui.screens.QuizScreen

@Composable
fun QuizApp(navController: NavHostController, questions: List<Question>) {
    QuizScreen(questions = questions, navController = navController)
}