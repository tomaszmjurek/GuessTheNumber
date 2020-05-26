package com.example.guessthenum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.guessthenum.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val LOADING_TIME:Long = 3000; // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))

            // close this activity
            finish()
        }, LOADING_TIME)
    }
}
