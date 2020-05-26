package com.example.guessthenum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import com.example.guessthenum.login.User
import com.example.guessthenum.sql.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ranking.*
import java.lang.Exception
import java.lang.NullPointerException
import kotlin.math.log

class RankingActivity : AppCompatActivity() {

    private lateinit var userDataBaseHelper : UserDatabaseHelper

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

        }

        returnBtn.setOnClickListener{onBackPressed()}
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
