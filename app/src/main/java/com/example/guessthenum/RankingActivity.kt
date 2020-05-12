package com.example.guessthenum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        for (i in 1..10) {
            var tvDynamic = TextView(this)
            tvDynamic.text = "$i. NICK  SCORE"
            tvDynamic.gravity = 1
            tvDynamic.textSize = 16F
            tvDynamic.layoutParams = ranking_list_layout.layoutParams
            ranking_list_layout.addView(tvDynamic)
        }

        returnBtn.setOnClickListener{onBackPressed()}
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
