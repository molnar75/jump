package hu.unimiskolc.iit.jump.framework.db.datasource

import hu.unimiskolc.iit.jump.core.data.datasource.ScoreDataSource
import hu.unimiskolc.iit.jump.core.domain.Score
import hu.unimiskolc.iit.jump.framework.db.dao.JumpScoreDao
import hu.unimiskolc.iit.jump.framework.db.mapper.JumpScoreMapper

class RoomJumpScoreDataSource(private val dao: JumpScoreDao, private val mapper: JumpScoreMapper): ScoreDataSource {
    override suspend fun add(score: Score) = dao.insert(mapper.mapToEntity(score))

    override suspend fun update(score: Score) = dao.update(mapper.mapToEntity(score))

    override suspend fun remove(score: Score) = dao.delete(mapper.mapToEntity(score))

    override suspend fun fetchAll(): List<Score> = dao.fetchAll().map {mapper.mapFromEntity(it)}
}