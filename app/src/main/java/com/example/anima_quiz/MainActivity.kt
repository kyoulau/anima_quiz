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
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.feature.data.model.QuestionList
import com.example.anima_quiz.feature.data.model.Player
import com.example.anima_quiz.feature.data.repository.QuestionRepository
import com.example.anima_quiz.feature.data.viewModel.QuizViewModelFactory
import com.example.anima_quiz.ui.screens.LeaderboardScreen

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

val samplePlayers = listOf(
    Player(nickname = "Player 1", score = 100, higherScore = 200),
    Player(nickname = "Player 2", score = 80, higherScore = 150),
    Player(nickname = "Player 3", score = 120, higherScore = 250),
    Player(nickname = "Player 4", score = 90, higherScore = 180),
    Player(nickname = "Player 5", score = 110, higherScore = 220),
    Player(nickname = "Player 6", score = 70, higherScore = 160),
    Player(nickname = "Player 7", score = 95, higherScore = 195),
    Player(nickname = "Player 8", score = 105, higherScore = 215),
    Player(nickname = "Player 9", score = 85, higherScore = 175),
    Player(nickname = "Player 10", score = 130, higherScore = 280),
)

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val questions = QuestionList().loadQuestion()

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
            QuizApp(navController, questions)
        }
    }
}
