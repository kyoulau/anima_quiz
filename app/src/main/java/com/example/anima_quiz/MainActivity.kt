package com.example.anima_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anima_quiz.ui.screens.MainScreen
import com.example.anima_quiz.ui.screens.Welcome
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anima_quiz.feature.data.database.QuizDatabase
import com.example.anima_quiz.feature.data.repository.QuestionRepository
import com.example.anima_quiz.feature.data.viewModel.QuizViewModelFactory
import com.example.anima_quiz.ui.theme.Anima_quizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = QuizDatabase.getDatabase(application).playerDao()
        val repository = QuestionRepository(dao)
        val factory =QuizViewModelFactory(repository)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            Welcome(onContinueClicked = {
                navController.navigate("main")
            })
        }
        composable("main") {
            MainScreen()
        }
    }
}

