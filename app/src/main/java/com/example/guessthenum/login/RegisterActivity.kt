package com.example.guessthenum.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthenum.R
import com.example.guessthenum.sql.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var userDatabaseHelper : UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activi)

        userDatabaseHelper = UserDatabaseHelper(this@RegisterActivity)
    }
}