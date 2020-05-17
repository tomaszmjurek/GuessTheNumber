package com.example.guessthenum.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.guessthenum.MainActivity
import com.example.guessthenum.R
import com.example.guessthenum.sql.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()
{
    private lateinit var userDatabaseHelper : UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        userDatabaseHelper = UserDatabaseHelper(this@LoginActivity)

        loginBtn.setOnClickListener(){ checkLogin() }
        registerLink.setOnClickListener(){ startActivity(Intent(this,RegisterActivity::class.java)) }
    }

    private fun checkLogin() {
        val name = nameInput.text.toString()
        val password = passwordInput.text.toString()

        if (name != "" && password != "") {
            if (userDatabaseHelper.userExists(name)) {
                if (userDatabaseHelper.userExists(name, password))
                    startActivity(Intent(this,MainActivity::class.java))
                else
                    makeToast("Wrong password!")
            } else {
                makeToast("User doesn't exists. Please register.")
            }
        } else {
            makeToast("Fields must not be empty!")
        }
    }

    private fun makeToast(text : String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }


}
