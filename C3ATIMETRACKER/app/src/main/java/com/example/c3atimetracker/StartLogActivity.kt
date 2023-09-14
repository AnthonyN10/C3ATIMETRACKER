package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StartLogActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_log)

        val pausedTime = TimeMainActivity.pausedTime
        val BtnStartLog: Button = findViewById(R.id.BtnStartLog)

        val TvLastSpent  = findViewById<TextView>(R.id.TvLastSpent)
        TvLastSpent.text = pausedTime

        BtnStartLog.setOnClickListener {

            intent = Intent(this, TimeMainActivity::class.java)
            startActivity(intent)

            // Button click action
        }
    }


}