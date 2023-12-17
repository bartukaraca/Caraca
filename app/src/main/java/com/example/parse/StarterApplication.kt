package com.example.parse

import android.app.Application
import com.parse.Parse


class StarterApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("myappID") // if desired
                .clientKey("DKoN5KDJW9p/")
                .server("http://51.20.32.152/parse/")
                .build()
        )
    }
}




