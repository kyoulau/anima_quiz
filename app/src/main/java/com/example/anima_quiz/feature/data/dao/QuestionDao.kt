package com.example.anima_quiz.feature.data.dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anima_quiz.feature.data.model.Question

interface QuestionDao {
    @Query("SELECT * FROM question")
    suspend fun getAllQuestions(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertAll(questions: List<Question>)
}