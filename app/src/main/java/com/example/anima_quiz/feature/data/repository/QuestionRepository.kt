package com.example.anima_quiz.feature.data.repository

import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.feature.data.dao.PlayerDao

class QuestionRepository(private val questionDao: PlayerDao) {
    suspend fun getAllQuestions():List<Question>{
        return questionDao.getAllQuestions()
    }

    suspend fun insertAllQuestion(question: List<Question>){
        questionDao.insertAll(question)
    }

}