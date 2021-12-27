package hu.unimiskolc.iit.jump.core.interactor

import hu.unimiskolc.iit.jump.core.data.repository.ScoreRepository
import hu.unimiskolc.iit.jump.core.domain.Score

class StartGame(private val scoreRepository: ScoreRepository) {
    suspend operator fun invoke() : List<Score> {
        val topScores = scoreRepository.fetchAll()
        topScores.sortedByDescending {it.value}
        topScores.take(10)
        return topScores
    }
}