package com.example.anima_quiz.feature.data.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anima_quiz.feature.data.model.Question
import com.example.anima_quiz.feature.data.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuestionRepository):ViewModel() {
    private val _questions = mutableStateOf<List<Question>>(emptyList())
    val questions: State<List<Question>> = _questions

    fun loadQuestions(){
        viewModelScope.launch {
            _questions.value = repository.getAllQuestions()
        }
    }

    fun insertQuestion(question: List<Question>){
        viewModelScope.launch {
            repository.insertAllQuestion(question)
            loadQuestions()
        }
    }

    // Aqui, questions é um State<List<Question>>,
    //e quando chamamos loadQuestions(),
    //o estado é atualizado e o Compose observa automaticamente a mudança.
}