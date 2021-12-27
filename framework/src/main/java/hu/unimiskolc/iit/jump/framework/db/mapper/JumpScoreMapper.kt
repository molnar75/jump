package hu.unimiskolc.iit.jump.framework.db.mapper

import hu.unimiskolc.iit.jump.core.domain.Score
import hu.unimiskolc.iit.jump.framework.db.entity.ScoreEntity

class JumpScoreMapper {
    fun mapToEntity(data: Score): ScoreEntity = ScoreEntity(data.id, data.date, data.value)
    fun mapFromEntity(entity: ScoreEntity): Score = Score(entity.id, entity.date, entity.value)
}