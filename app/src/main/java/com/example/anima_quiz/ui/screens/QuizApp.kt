package com.example.anima_quiz.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.anima_quiz.R
import com.example.anima_quiz.data.QuizQuestion

@Composable
fun QuizApp(navController: NavHostController) {
    val quizQuestions = listOf(
        QuizQuestion(
            image = R.drawable.hk1,
            question = "Which Sanrio character is known for their love of cooking and has a pet panda?",
            answers = listOf("Pompompurin", "My Melody", "Kuromi", "Cinnamoroll", "Hello Kitty"),
            correctAnswer = "Pompompurin"
        ),
        QuizQuestion(
            image = R.drawable.hk2,
            question = "What is the name of the city where most Sanrio characters live?",
            answers = listOf("Tokyo", "Kyoto", "Osaka", "Shirokuma-yama", "London"),
            correctAnswer = "Shirokuma-yama"
        ),
        QuizQuestion(
            image = R.drawable.hk3,
            question = "Which Sanrio character is known for a rebellious personality with a soft heart inside?",
            answers = listOf("Kuromi", "Cinnamoroll", "Chococat", "Pompompurin", "Bad Badtz Maru"),
            correctAnswer = "Kuromi"
        ),
        QuizQuestion(
            image = R.drawable.jjk1,
            question = "Which Jujutsu Kaisen character is known for speaking very little?",
            answers = listOf("Panda", "Toge Inumaki", "Maki Zenin", "Yuta Okotsu"),
            correctAnswer = "Toge Inumaki"
        ),
        QuizQuestion(
            image = R.drawable.jjk2,
            question = "Which character uses a technique involving voodoo dolls?",
            answers = listOf("Nobara Kugisaki", "Megumi Fushiguro", "Satoro Gojou", "Yuji Itadori"),
            correctAnswer = "Nobara Kugisaki"
        ),
        QuizQuestion(
            image = R.drawable.jjk3,
            question = "How much does Gojo's T-shirt cost?",
            answers = listOf("10,000 yen", "57,000 yen", "250,000 yen", "499,000 yen"),
            correctAnswer = "250,000 yen"
        ),
        QuizQuestion(
            image = R.drawable.bsk1,
            question = "Which Berserk character did not appear in the 80s anime but appears in later adaptations and the manga?",
            answers = listOf("Guts (with armor)", "Cascka (green cloak)", "Puck (fairy)", "Farnese (blonde)", "Serpico (blonde)", "Schierke (witch)", "Isidro (young boy)"),
            correctAnswer = "Puck (fairy)"
        ),
        QuizQuestion(
            image = R.drawable.bsk2,
            question = "What is the name of the sword used by Guts?",
            answers = listOf("Dragon Slayer", "Great Sword", "Mortal Blade", "Chaos Blade", "Excalibur"),
            correctAnswer = "Dragon Slayer"
        ),
        QuizQuestion(
            image = R.drawable.bsk3,
            question = "What side effects can Guts' Berserk armor cause?",
            answers = listOf("Fatigue", "Loss of body parts", "None, the armor causes nothing", "It consumes his body and mind"),
            correctAnswer = "It consumes his body and mind"
        ),
        QuizQuestion(
            image = R.drawable.jojo1,
            question = "What object does Dio throw at Jotaro?",
            answers = listOf("Trash can", "Steamroller", "Concrete piece", "Sofa"),
            correctAnswer = "Steamroller"
        ),
        QuizQuestion(
            image = R.drawable.jojo2,
            question = "What is Yukako Yamagishi's Stand?",
            answers = listOf("Lovers", "The Hand", "Love Deluxe", "Echoes"),
            correctAnswer = "Love Deluxe"
        )
    )


    QuizScreen(questions = quizQuestions, navController = navController)
}