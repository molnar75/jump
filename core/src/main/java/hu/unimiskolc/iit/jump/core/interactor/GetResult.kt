package hu.unimiskolc.iit.jump.core.interactor

import hu.unimiskolc.iit.jump.core.data.repository.ScoreRepository
import hu.unimiskolc.iit.jump.core.domain.Score

class GetResult(private val scoreRepository: ScoreRepository) {
    suspend operator fun invoke(): List<Score> {
        val allScores = scoreRepository.fetchAll()
        val sortedList = allScores.sortedByDescending { it.value }
        return sortedList.take(5)
    }
}