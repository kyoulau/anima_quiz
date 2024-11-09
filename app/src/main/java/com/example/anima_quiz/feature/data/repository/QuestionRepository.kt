package com.example.anima_quiz.feature.data.repository

import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.feature.data.dao.PlayerDao
import com.example.anima_quiz.feature.data.dao.QuestionDao

class QuestionRepository(private val questionDao: QuestionDao) {
    suspend fun getAllQuestions():List<Question>{
        return questionDao.getAllQuestions()
    }

    suspend fun insertAllQuestion(question: List<Question>){
        questionDao.insertAll(question)
    }

}