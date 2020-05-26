package com.example.guessthenum

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthenum.sql.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_ranking.*
import java.util.logging.Logger

class RankingActivity : AppCompatActivity() {

    private lateinit var userDataBaseHelper : UserDatabaseHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        userDataBaseHelper = UserDatabaseHelper(this@RankingActivity)

        try {
            var bestUsers = userDataBaseHelper.getTenBestUsers()

            bestUsers.forEachIndexed { index, it ->
                var tvDynamic = TextView(this)
                tvDynamic.text = "${index+1}. '${it.name}' with score: ${it.score}"
                tvDynamic.gravity = 1
                tvDynamic.textSize = 20F
                tvDynamic.layoutParams = ranking_list_layout.layoutParams
                ranking_list_layout.addView(tvDynamic)
            }
        } catch (e : NullPointerException) {
            log.info("Ranking list is null - $e")
        }

        returnBtn.setOnClickListener{onBackPressed()}
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        val log: Logger = Logger.getLogger(RankingActivity::class.java.simpleName)
    }
}
