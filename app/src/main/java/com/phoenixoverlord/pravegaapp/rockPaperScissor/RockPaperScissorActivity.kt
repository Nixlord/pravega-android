package com.phoenixoverlord.pravegaapp.rockPaperScissor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.phoenixoverlord.pravegaapp.R
import kotlinx.android.synthetic.main.activity_rock_paper_scissor.*


class RockPaperScissorActivity : AppCompatActivity()  {

    companion object {
        const val WINNER = "WINNER"
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissor)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        viewModel.initializeViewModel()

        userRock.setOnClickListener{
            userSelectedOption.setImageResource(R.drawable.rock)
            viewModel.userClicked(Selection.ROCK)
        }

        userPaper.setOnClickListener {
            userSelectedOption.setImageResource(R.drawable.paper)
            viewModel.userClicked(Selection.PAPER)
        }

        userScissor.setOnClickListener {
            userSelectedOption.setImageResource(R.drawable.scissor)
            viewModel.userClicked(Selection.SCISSOR)
        }

        viewModel.userScore.observe(this, Observer {
            userScore.text = "Your Score: $it"
        })

        viewModel.computerScore.observe(this, Observer {
            computerScore.text = "Computer Score $it"
        })

        viewModel.roundNumber.observe(this, Observer {
            roundNumber.text = "ROUND $it"
        })

        viewModel.winnerName.observe(this, Observer {
            finalResults.text = it
        })

        viewModel.computerImage.observe(this, Observer {
            Handler().postDelayed(
                {
                    when(it) {
                        Selection.ROCK -> { computerSelectedOption.setImageResource(R.drawable.rock) }
                        Selection.PAPER -> { computerSelectedOption.setImageResource(R.drawable.paper) }
                        Selection.SCISSOR -> { computerSelectedOption.setImageResource(R.drawable.scissor) }
                        else -> {}
                    }
                },
                300
            )

        })

        viewModel.endActivity.observe(this, Observer {
            if(it == false) return@Observer
            userRock.setOnClickListener(null)
            userPaper.setOnClickListener(null)
            userScissor.setOnClickListener(null)
            Handler().postDelayed(
                {
                    val intent = Intent()
                    intent.putExtra(WINNER, viewModel.winnerName.value!!)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                },
                600
            )
        })

        viewModel.winnerImage.observe(this, Observer {
            Handler().postDelayed(
                {
                    when (it) {
                        "Paper" -> finalResultsImage.setImageResource(R.drawable.paper_wins)
                        "Rock" -> finalResultsImage.setImageResource(R.drawable.rock_wins)
                        "Scissor" -> finalResultsImage.setImageResource(R.drawable.scissor_wins)
                        else -> finalResultsImage.setImageResource(R.drawable.placeholder)
                    }
                },
                300
            )
        })
    }
}