package com.example.anima_quiz.feature.data.model
import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val imageUrl: Int,
    val tips: List<String>
){
    fun randomizeOptions(): Question {
        val shuffledOptions = options.shuffled()
        val newCorrectAnswerIndex = shuffledOptions.indexOf(options[correctAnswerIndex])
        return copy(options = shuffledOptions, correctAnswerIndex = newCorrectAnswerIndex)
    }
}

