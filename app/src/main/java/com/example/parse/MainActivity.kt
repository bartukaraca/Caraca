package com.example.parse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.parse.databinding.ActivityMainBinding
import com.parse.LogInCallback
import com.parse.ParseAnalytics
import com.parse.ParseException
import com.parse.ParseUser


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ParseAnalytics.trackAppOpenedInBackground(intent)



    }

    fun signIn(view:View){

        ParseUser.logInInBackground(binding.usernameText.text.toString(),binding.passwordText.text.toString(), LogInCallback { user, e ->

            if (e!= null) {
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Hoşgeldin " + user.username.toString(),Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
            }


        })
    }

    fun signUp(view:View){
        val intent = Intent(applicationContext,SignUpActivitiy::class.java)
        startActivity(intent)
    }


}