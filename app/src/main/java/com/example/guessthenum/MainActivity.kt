package com.example.guessthenum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.guessthenum.login.LoginActivity
import kotlin.random.Random
import kotlinx.android.synthetic.main. activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var toGuess = -1
    private var tries = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nickName = intent.getStringExtra("nickName")
        nickNameText.text = "Playing as: $nickName"

        SeeRankingBtn.setOnClickListener{goToRanking()}
        logoutBtn.setOnClickListener{startActivity(Intent(this, LoginActivity()::class.java))}
        newGameBtn.setOnClickListener{newGame()}
        guessBtn.setOnClickListener {
            try {
                checkAnswer()
            } catch (e : Exception) {
                // no input value
            }}
        newGame()
    }

    private fun newGame() {
        toGuess = Random.nextInt(0, 20)
        tries = 0
        infoText.text = ""
        triesText.text = ""
    }

    private fun checkAnswer() {
        var guessed = numberText.text.toString().toInt()
        if (guessed < 0 || guessed > 20) {
            makeToast("Number must be between 0 and 20")
        } else if (guessed == toGuess) {
            tries++
            calculateScore()
            makeToast("Good job! You got it after $tries tries.")
            newGame()
        } else {
            if (guessed < toGuess) infoText.text = "Wrong. Try something greater..."
            else infoText.text = "Wrong. Try something lesser..."
            tries++
        }
        triesText.text = tries.toString()

        if(tries > 20) {
            makeToast("You have lost! Try again.")
            newGame()
        }
    }

    private fun calculateScore() {
        when {
            tries >= 7 -> {
                score = 1
            }
            tries >= 5 -> {
                score = 2
            }
            tries >= 2 -> {
                score = 3
            }
            tries == 1 -> {
                score = 5
            }
        }
    }

    private fun goToRanking() {
        startActivity(Intent(this,RankingActivity::class.java))
    }

    private fun makeToast(text : String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
