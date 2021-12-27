package hu.unimiskolc.iit.jump.core.data.repository

import hu.unimiskolc.iit.jump.core.data.datasource.ScoreDataSource
import hu.unimiskolc.iit.jump.core.domain.Score

class ScoreRepository(private val dataSource: ScoreDataSource) {
    suspend fun add(score: Score) = dataSource.add(score)
    suspend fun update(score: Score) = dataSource.update(score)
    suspend fun remove(score: Score) = dataSource.remove(score)
    suspend fun fetchAll(): List<Score> = dataSource.fetchAll()
}