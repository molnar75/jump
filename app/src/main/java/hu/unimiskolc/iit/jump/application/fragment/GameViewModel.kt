package hu.unimiskolc.iit.jump.application.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val gameStarted: MutableLiveData<Boolean> = MutableLiveData()
    private val gameEnded: MutableLiveData<Boolean> = MutableLiveData()

    fun getGameStarted(): LiveData<Boolean> = gameStarted

    fun setGameStarted(value: Boolean) {
        gameStarted.value = value
    }

    fun getGameEnded(): LiveData<Boolean> = gameEnded

    fun setGameEnded(value: Boolean) {
        gameEnded.value = value
    }
}