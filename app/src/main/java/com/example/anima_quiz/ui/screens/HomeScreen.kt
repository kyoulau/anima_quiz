package com.example.anima_quiz.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.anima_quiz.ui.components.Greeting
import com.example.anima_quiz.ui.theme.Anima_quizTheme


@Composable
fun HomeScreen() {
    Scaffold {
        val innerPadding = it
        Greeting(name = "Home Screen")
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Anima_quizTheme {
        HomeScreen()
    }


}