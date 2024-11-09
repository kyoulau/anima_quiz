package com.example.anima_quiz.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.anima_quiz.feature.data.dao.PlayerDao
import com.example.anima_quiz.feature.data.dao.QuestionDao
import com.example.anima_quiz.feature.data.model.Converters
import com.example.anima_quiz.feature.data.model.Player
import com.example.anima_quiz.feature.data.model.Question // Ensure you have this import

@Database(entities = [Player::class, Question::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class) // Use your own Converters class
abstract class QuizDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao // Ensure you have the correct DAOs
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database" // Optionally, rename to reflect all entities
                )
                    .fallbackToDestructiveMigration() // Handle migrations appropriately
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
