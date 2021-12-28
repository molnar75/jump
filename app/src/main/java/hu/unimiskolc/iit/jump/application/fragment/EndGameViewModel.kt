package hu.unimiskolc.iit.jump.application.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.unimiskolc.iit.jump.core.domain.Score
import hu.unimiskolc.iit.jump.core.interactor.JumpInteractors
import kotlinx.coroutines.launch

class EndGameViewModel(private val interactors: JumpInteractors) : ViewModel() {
    private val score: MutableLiveData<Int> = MutableLiveData()
    private val highScoreList: MutableLiveData<List<Score>> = MutableLiveData()

    fun getScore() : LiveData<Int> = score

    fun getHighScoreList() : LiveData<List<Score>> = highScoreList

    fun setScore(value: Int) {
        score.postValue(value)
    }

    fun setHighScoreList(list: List<Score>) {
        highScoreList.postValue(list)
    }

    fun getResult() {
        viewModelScope.launch {
            val resultList = interactors.getResult()
            highScoreList.postValue(resultList)
        }
    }
}