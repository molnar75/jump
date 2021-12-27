package hu.unimiskolc.iit.jump.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.unimiskolc.iit.jump.framework.db.converter.DateTypeConverter
import hu.unimiskolc.iit.jump.framework.db.dao.JumpScoreDao
import hu.unimiskolc.iit.jump.framework.db.entity.ScoreEntity

@Database(
    entities = [ScoreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateTypeConverter::class
)
abstract class JumpDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "quiz.db"
        private var instance: JumpDatabase? = null

        private fun create(context: Context) : JumpDatabase = Room.databaseBuilder(context, JumpDatabase::class.java, DATABASE_NAME)
            .build()

        fun getInstance(context: Context) : JumpDatabase = (instance ?: create(context)).also { instance = it }
    }

    abstract fun jumpScoreDao(): JumpScoreDao
}