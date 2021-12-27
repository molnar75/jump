package hu.unimiskolc.iit.jump.framework.db.dao

import androidx.room.*
import hu.unimiskolc.iit.jump.framework.db.entity.ScoreEntity

@Dao
interface JumpScoreDao {
    @Insert
    suspend fun insert(score: ScoreEntity)

    @Update
    suspend fun update(score: ScoreEntity)

    @Delete
    suspend fun delete(score: ScoreEntity)

    @Query("SELECT * FROM jump_score")
    suspend fun fetchAll(): List<ScoreEntity>
}