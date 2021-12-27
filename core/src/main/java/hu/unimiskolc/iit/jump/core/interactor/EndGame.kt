package hu.unimiskolc.iit.jump.core.interactor

import hu.unimiskolc.iit.jump.core.data.repository.ScoreRepository
import hu.unimiskolc.iit.jump.core.domain.Score

class EndGame(private val scoreRepository: ScoreRepository) {
    suspend operator fun invoke(score: Score) {
        scoreRepository.add(score)
    }
}