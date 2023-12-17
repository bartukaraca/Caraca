package com.example.parse

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parse.databinding.ActivityDetailBinding
import com.example.parse.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery

class DetailActivity : AppCompatActivity() {


    var chosenPlace = ""

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val intent = intent
        chosenPlace = intent.getStringExtra("name").toString()



        val query = ParseQuery<ParseObject>("Arabalar")
        query.whereEqualTo("name",chosenPlace)
        query.findInBackground { objects, e ->
            if (e != null) {
                Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
            } else {

                if (objects.size > 0) {
                    for (parseObject in objects) {
                        val image = parseObject.get("image") as ParseFile
                        image.getDataInBackground { data, e ->
                            if (e != null) {
                                Toast.makeText(
                                    applicationContext,
                                    e.localizedMessage,
                                    Toast.LENGTH_LONG
                                ).show()

                            } else {

                                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                                binding.imageView.setImageBitmap(bitmap)
                                val name = parseObject.get("name") as String
                                val model = parseObject.get("model") as String
                                val ozellikler = parseObject.get("ozellikler") as String

                                binding.textView.text = name
                                binding.textView2.text = model
                                binding.textView3.text = ozellikler


                            }
                        }
                    }

                }


            }
        }
        }

        }
