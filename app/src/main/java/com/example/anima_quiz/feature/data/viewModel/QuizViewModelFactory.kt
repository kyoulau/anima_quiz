package com.example.anima_quiz.feature.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.anima_quiz.feature.data.repository.QuestionRepository

class QuizViewModelFactory(private val repository: QuestionRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = QuizViewModel(repository) as T
}