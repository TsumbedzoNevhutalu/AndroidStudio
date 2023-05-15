package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen);
//        adding function to the splash screen

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splashscreen)//instruct the layout tp start in the splash screen

        Handler().postDelayed({
            // on below line we are
            // creating a new intent
            val i = Intent(
                this@SplashscreenActivity,
                RegisterActivity::class.java
            )
            // starting a new activity.
            startActivity(i)

            //
            // finishing the current activity.
            finish()
        }, 4000)

    }
}