package hu.unimiskolc.iit.jump.application.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unimiskolc.iit.jump.core.domain.Score
import hu.unimiskolc.iit.jump.core.interactor.JumpInteractors
import kotlinx.coroutines.launch

class GameViewModel(private val interactors: JumpInteractors) : ViewModel() {

    fun endGame(score: Score) {
        viewModelScope.launch {
            interactors.endGame(score)
        }
    }
}