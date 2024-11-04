package com.example.anima_quiz.feature.data.model
import com.example.anima_quiz.R
class QuestionList {
    fun loadQuestion(): List<Question> = listOf(
        Question(
            questionText = "Qual destes personagens da Sanrio é conhecido por sua paixão por cozinhar e por ter um panda de estimação?",
            options = listOf("Pompompurin", "My Melody","Kuromi","Cinnamoroll","Hello Kitty"),
            correctAnswerIndex = 0,
            imageUrl = R.drawable.hk3,
            tips = listOf("Personagem amarelo", "Amigo da Hello Kitty")
        ),
        Question(
            questionText = " Qual personagem adora andar de skate??",
            options = listOf("Chococat", "Badtz-Maru","Hello Kitty","Cinnamoroll","Pompompurin"),
            correctAnswerIndex = 1,
            imageUrl = R.drawable.hk2,
            tips = listOf("É um pinguim muito radical", "Amigo da Hello Kitty")
        ),
        Question(
            questionText = "Qual personagem da Sanrio é conhecido por sua personalidade rebelde e por ter um coração macio por dentro?",
            options = listOf("Kuromi", "Cinnamoroll","Chococat", "Pompompurin", " Bad Badtz Maru"),
            correctAnswerIndex = 0,
            imageUrl = R.drawable.hk1,
            tips = listOf("Coelhinha fofa que usa uma touca preta","Faz aniversário na Halloween")
        )
    )
}