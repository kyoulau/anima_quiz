package com.example.anima_quiz.feature.data.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anima_quiz.feature.data.dao.PlayerDao
import com.example.anima_quiz.feature.data.model.Player

@Database(entities = [Player::class], version = 2)
@TypeConverters(Converters::class)
abstract class QuizDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object{
        @Volatile
        private var INSTANCE : QuizDatabase ? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "question_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
