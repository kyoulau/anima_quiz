package com.example.anima_quiz.feature.data.model

import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.room.Entity
import  androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,

    val nickname: String,

    val score: Int,

    val higherScore: Int
)
