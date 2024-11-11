package com.example.anima_quiz.feature.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anima_quiz.feature.data.model.Player
import com.example.anima_quiz.feature.data.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PlayerViewModel(private val repository: PlayerRepository) : ViewModel() {

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    init {
        // Load players when the ViewModel is created
        loadPlayers()
    }

    private fun loadPlayers() {
        viewModelScope.launch {
            _players.value = repository.getAllPlayers() // Update the state flow
        }
    }

    fun insertOrUpdatePlayer(player: Player) = viewModelScope.launch {
        val existingPlayer = repository.getPlayerByNickname(player.nickname)

        if (existingPlayer != null) {
            // Verifica se o novo score é maior que o higherScore atual
            val updatedHigherScore = if (player.score > existingPlayer.higherScore) {
                player.score
            } else {
                existingPlayer.higherScore
            }

            // Atualiza o jogador com o novo score e, se necessário, o higherScore
            val updatedPlayer = existingPlayer.copy(
                score = player.score,
                higherScore = updatedHigherScore
            )
            repository.updatePlayer(updatedPlayer)
        } else {
            // Jogador não existe, então insere um novo
            repository.insertPlayer(player)
        }
        loadPlayers()
    }

    fun clearLeaderboard() = viewModelScope.launch {
        repository.deleteAllPlayers() // Apaga todos os jogadores
        loadPlayers() // Recarrega os dados após a exclusão
    }


}
