package hu.unimiskolc.iit.jump.core.data.datasource

import hu.unimiskolc.iit.jump.core.domain.Score

interface ScoreDataSource {
    suspend fun add(score: Score)
    suspend fun update(score: Score)
    suspend fun remove(score: Score)
    suspend fun fetchAll(): List<Score>
}