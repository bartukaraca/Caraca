package com.example.parse

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.parse.databinding.ActivityPlaceNameBinding
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.ByteArrayOutputStream


private lateinit var binding: ActivityPlaceNameBinding

class PlaceNameActivity : AppCompatActivity() {
    var globalName = ""
    var globalType = ""
    var globalAtmosphere = ""
    var globalImage : Bitmap? = null
    private lateinit var binding: ActivityPlaceNameBinding
    var chosenImage : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceNameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun selectimage(view: View){


        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,1)

        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

            if (requestCode == 2) {
                if (grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(intent,1)
                }
            }

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val selected = data.data

            try {

                chosenImage = MediaStore.Images.Media.getBitmap(this.contentResolver,selected)
                binding.imageView.setImageBitmap(chosenImage)


            } catch (e: Exception) {
                e.printStackTrace()
            }



        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun next(view: View) {
        globalImage = chosenImage
        globalName = binding.nameDetailText.text.toString()
        globalType = binding.typeText.text.toString()
        globalAtmosphere = binding.atmosphereText.text.toString()

        val byteArrayOutputStream = ByteArrayOutputStream()
        globalImage!!.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream)
        val bytes = byteArrayOutputStream.toByteArray()

        val parseFile = ParseFile("image.png",bytes)

        val parseObj = ParseObject("Arabalar")
        parseObj.put("name", globalName)
        parseObj.put("model", globalType)
        parseObj.put("ozellikler", globalAtmosphere)

        parseObj.put("username", ParseUser.getCurrentUser().username.toString())
        parseObj.put("image",parseFile)
        parseObj.saveInBackground { e ->
            if (e != null) {
                Toast.makeText(applicationContext,e.localizedMessage, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext,"Araba Kaydedildi", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, LocationsActivity::class.java)
                startActivity(intent)
            }
        }


    }
}