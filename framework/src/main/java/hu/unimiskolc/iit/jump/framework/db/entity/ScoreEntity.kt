package hu.unimiskolc.iit.jump.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "jump_score"
)
data class ScoreEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val date: Date,
    val value: Int
)