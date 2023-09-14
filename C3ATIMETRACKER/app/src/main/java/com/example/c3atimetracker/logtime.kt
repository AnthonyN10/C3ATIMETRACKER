package com.example.c3atimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class logtime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logtime)


        val newlog = findViewById<Button>(R.id.newlog)
        val btnLoad = findViewById<Button>(R.id.DailyGoalBtn)

        newlog.setOnClickListener {

            val intent = Intent(this, StartLogActivity::class.java);
            startActivity(intent)
        }


        btnLoad.setOnClickListener {

                val intent = Intent(this, MinmaxActivity::class.java);
            startActivity(intent)


        }
    }
}