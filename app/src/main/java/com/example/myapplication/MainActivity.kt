package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button

private lateinit var btnMenu: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

////        btnMenu = findViewById(R.id.btnMenu)
////
////        btnMenu.setOnClickListener{
////            intent = Intent(this, Menu::class.java)
////            startActivity(intent)
//        }


    }
}