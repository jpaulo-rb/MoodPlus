package br.com.project.moodplus.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.project.moodplus.model.Mood

@Database(entities = [Mood::class], version = 1)
abstract class DbAcess : RoomDatabase() {

    abstract fun moodDao(): MoodDao

    companion object {

        private lateinit var instance: DbAcess

        fun getDb(context: Context): DbAcess {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        DbAcess::class.java,
                        "moodplus_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(false)
                    .build()
            }
            return instance
        }
    }

}