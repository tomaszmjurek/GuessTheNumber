package com.example.guessthenum.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.guessthenum.R
import com.example.guessthenum.sql.UserDatabaseHelper
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var userDatabaseHelper : UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        userDatabaseHelper = UserDatabaseHelper(this@RegisterActivity)

        registerBtn.setOnClickListener(){ registerCheck() }
        loginLink.setOnClickListener(){ startActivity(Intent(this,LoginActivity::class.java)) }
    }

    private fun registerCheck() {
        val name = nameInput.text.toString()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (name != "" && password != "" && confirmPassword != "") {
            if (userDatabaseHelper.userExists(name)) {
                makeToast("This user already exists!")
            } else {
                if (password != confirmPassword)
                    makeToast("Passwords must match each other!")
                else {
                    userDatabaseHelper.addUser(User(-1, name, password))
                    makeToast("New user registered")
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        } else {
            makeToast("Fields must not be empty!")
        }
    }

    private fun makeToast(text : String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}