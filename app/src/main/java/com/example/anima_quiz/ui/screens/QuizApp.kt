package com.example.anima_quiz.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.anima_quiz.data.QuizQuestion

@Composable
fun QuizApp(navController: NavHostController) {
    val quizQuestions = listOf(
        QuizQuestion(
            question = "What is the capital of France?",
            answers = listOf("Paris", "Berlin", "Madrid", "Rome"),
            correctAnswer = "Paris"
        ),
        QuizQuestion(
            question = "Which planet is known as the Red Planet?",
            answers = listOf("Earth", "Mars", "Jupiter", "Saturn"),
            correctAnswer = "Mars"
        ),
        QuizQuestion(
            question = "Who wrote 'Hamlet'?",
            answers = listOf("Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"),
            correctAnswer = "William Shakespeare"
        ),
        QuizQuestion(
            question = "What is the largest ocean on Earth?",
            answers = listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
            correctAnswer = "Pacific Ocean"
        )
    )

    QuizScreen(questions = quizQuestions, navController = navController)
}