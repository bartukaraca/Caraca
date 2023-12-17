package com.example.parse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.parse.databinding.ActivityMainBinding
import com.example.parse.databinding.ActivitySignUpActivitiyBinding
import com.parse.ParseAnalytics
import com.parse.ParseException
import com.parse.ParseUser

class SignUpActivitiy : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpActivitiyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpActivitiyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ParseAnalytics.trackAppOpenedInBackground(intent)
    }





    fun signUp2(view : View){

        val user = ParseUser()
        user.username= binding.usernameSignUpText.text.toString()
        user.setPassword(binding.passwordSignUpText.text.toString())
        user.signUpInBackground{e: ParseException? ->
            if (e != null) {
                Toast.makeText(applicationContext,e.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext,"User Created", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
            }

        }



    }
}