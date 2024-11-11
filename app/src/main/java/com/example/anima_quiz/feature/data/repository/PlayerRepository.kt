package com.example.anima_quiz.feature.data.repository

import com.example.anima_quiz.feature.data.dao.PlayerDao
import com.example.anima_quiz.feature.data.model.Player

class PlayerRepository(private val playerDao: PlayerDao) {

    suspend fun insertPlayer(player: Player) {
        playerDao.insertPlayer(player)
    }

    suspend fun getAllPlayers(): List<Player> {
        return playerDao.getAllPlayers()
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }

    suspend fun deleteAllPlayers() {
        playerDao.deleteAllPlayers()
    }

    suspend fun getPlayerByNickname(nickname: String): Player? {
        return playerDao.getPlayerByNickname(nickname)
    }
}
