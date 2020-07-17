package com.phoenixoverlord.pravegaapp.rockPaperScissor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.phoenixoverlord.pravegaapp.mechanisms.EvaVoice
import java.util.*

class GameViewModel : ViewModel() {

    init {
        Log.i("GameViewModel", "GameViewModel created!")
    }

    private var _computerScore = MutableLiveData<Int>()
    val computerScore: LiveData<Int>
        get() = _computerScore

    private var _userScore = MutableLiveData<Int>()
    val userScore: LiveData<Int>
        get() = _userScore

    private var _roundNumber = MutableLiveData<Int>()
    val roundNumber: LiveData<Int>
        get() = _roundNumber

    private var _endActivity = MutableLiveData<Boolean>()
    val endActivity: LiveData<Boolean>
        get() = _endActivity

    private var _computerImage = MutableLiveData<Selection>()
    val computerImage: LiveData<Selection>
        get() = _computerImage

    private var _winnerName = MutableLiveData<String>()
    val winnerName: LiveData<String>
        get() = _winnerName

    private var _winnerImage = MutableLiveData<String>()
    val winnerImage: LiveData<String>
        get() = _winnerImage

    private var localUserScore = 0
    private var localComputerScore = 0
    private var localRoundNumber = 1

    fun initializeViewModel() {
        _computerScore.value = localComputerScore
        _userScore.value = localUserScore
        _roundNumber.value = localRoundNumber
    }

    fun userClicked(userSelection: Selection) {

        val computerSelection = getRandomComputerSelection()
        _computerImage.value = computerSelection
        checkResults(userSelection, computerSelection)

    }

    private fun getRandomComputerSelection(): Selection {
        when (Random().nextInt(3)) {
            0 -> return Selection.ROCK
            1 -> return Selection.PAPER
            2 -> return Selection.SCISSOR
        }
        return Selection.ROCK
    }

    private fun checkResults(userSelection: Selection, computerSelection: Selection) {
        if (userSelection == computerSelection) {
            _winnerName.value = "DRAW MATCH"
            _winnerImage.value = "Draw"
        } else if(userSelection == Selection.ROCK) { //rock wins from scissor, loose from paper
            if(computerSelection == Selection.PAPER) {
                localComputerScore += 1
                _computerScore.value = localComputerScore
                _winnerName.value = "Eva wins"
                _winnerImage.value = "Paper"
            } else if (computerSelection == Selection.SCISSOR) {
                localUserScore += 1
                _userScore.value = localUserScore
                _winnerName.value = "You win"
                _winnerImage.value = "Rock"
            }
        } else if (userSelection == Selection.PAPER) { //paper wins from rock, loose from scissor
            if (computerSelection == Selection.SCISSOR) {
                localComputerScore += 1
                _computerScore.value = localComputerScore
                _winnerName.value = "Eva wins"
                _winnerImage.value = "Scissor"
            } else if (computerSelection == Selection.ROCK) {
                localUserScore += 1
                _userScore.value = localUserScore
                _winnerName.value = "You win"
                _winnerImage.value = "Paper"
            }
        } else if (userSelection == Selection.SCISSOR) { //scissor wins from paper, loose from rock
            if (computerSelection == Selection.ROCK) {
                localComputerScore += 1
                _computerScore.value = localComputerScore
                _winnerName.value = "Eva wins"
                _winnerImage.value = "Rock"
            } else if (computerSelection == Selection.PAPER) {
                localUserScore += 1
                _userScore.value = localUserScore
                _winnerName.value = "You win"
                _winnerImage.value = "Scissor"
            }
        }

        if(localRoundNumber != 5) {
            localRoundNumber += 1
            _roundNumber.value = localRoundNumber
        } else {
            _endActivity.value = true
        }

        Log.e("cpu selected", computerSelection.toString())
        Log.e("user selected", userSelection.toString())
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

}