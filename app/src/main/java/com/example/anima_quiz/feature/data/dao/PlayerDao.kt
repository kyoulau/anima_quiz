package com.example.anima_quiz.feature.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.anima_quiz.feature.data.model.Player

interface PlayerDao {
    @Insert
    suspend fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun listPlayers(players: List<Player>)

    @Query("SELECT * FROM players ORDER BY score DESC")
    fun getAllPlayers(): List<Player>

    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers()

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM players WHERE nickname = :nickname")
    suspend fun getPlayerByNickname(nickname: String): Player?
}
