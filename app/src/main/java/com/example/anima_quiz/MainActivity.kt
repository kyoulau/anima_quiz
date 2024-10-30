package com.example.anima_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.anima_quiz.ui.components.Greeting
import com.example.anima_quiz.ui.screens.HomeScreen
import com.example.anima_quiz.ui.theme.Anima_quizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Anima_quizTheme {
                HomeScreen()
            }
        }
    }
}



