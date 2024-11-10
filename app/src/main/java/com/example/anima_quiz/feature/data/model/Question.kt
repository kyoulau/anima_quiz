package com.example.anima_quiz.feature.data.model
import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Question(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val questionText: String,
    var options: List<String>,
    var correctAnswerIndex: Int,
    val imageUrl: Int,
    val tips: List<String>
){
    fun randomizeOptions() {
        val shuffledOptions = options.shuffled()
        val newCorrectAnswerIndex = shuffledOptions.indexOf(options[correctAnswerIndex])
        this.options = shuffledOptions
        this.correctAnswerIndex = newCorrectAnswerIndex

    }
}

