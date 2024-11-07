package com.example.anima_quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anima_quiz.ui.QuizApp
import com.example.anima_quiz.ui.screens.MainScreen
import com.example.anima_quiz.ui.screens.Welcome
import com.example.anima_quiz.feature.data.database.QuizDatabase
import com.example.anima_quiz.feature.data.repository.QuestionRepository
import com.example.anima_quiz.feature.data.viewModel.QuizViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = QuizDatabase.getDatabase(application).questionDao()
        val repository = QuestionRepository(dao)
        val factory = QuizViewModelFactory(repository)
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
            MainScreen { userName ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "userName",
                    userName
                )
                navController.navigate("quizScreen")
            }
        }
        composable("quizScreen") {
            QuizApp(navController)
        }
    }
}

