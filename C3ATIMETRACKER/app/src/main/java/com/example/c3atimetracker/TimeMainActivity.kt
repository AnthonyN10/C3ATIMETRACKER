package com.example.c3atimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class TimeMainActivity : AppCompatActivity() {
    private var seconds = 0
    private var running = false
    private var wasRunning = false
    private var time: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_main)
        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds")
            savedInstanceState.getBoolean("running")
            savedInstanceState.getBoolean("wasRunning")
        }
        val backBtn: Button = findViewById(R.id.backBtn)

        runTimer()

        backBtn.setOnClickListener {

            if (!running) {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please stop the time first!", Toast.LENGTH_SHORT).show()
            }
            // Button click action
        }
    }

    //Start button Functionality
    fun onStart(view: View?) {
        running = true
    }

    //Stop button Functionality

    fun onStop(view: View?) {
        running = false
        pausedTime = time

        // Display a toast message
        Toast.makeText(this, "Time saved", Toast.LENGTH_SHORT).show()
    }


    //Reset button Functionality
    fun onReset(view: View?) {
        running = false
        seconds = 0
    }

    //Pause button Functionality
    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    //Resume button Functionality
    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    //Overriding and saving instance on screen data Functionality
    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }

    //Running the timer Functionality
    private fun runTimer() {
        val timeView = findViewById<TextView>(R.id.textview) //Finding the timer text view
        val handler = Handler() //Functionality call handler
        handler.post(object : Runnable {
            override fun run() //When the timer is running the following methods will explain how it should be done.
            {
                val hours = seconds / 3600 //how hours are calculated
                val minutes = seconds % 3600 / 60 //how the minutes are calculated
                val secs = seconds % 60 //how the time in seconds is calculated
                time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d",
                    hours,
                    minutes,
                    secs
                ) // the format will follow this here

                //Call the time string to the "timeView" which is the name of the textView in the xml file
                timeView.text = time
                if (running) //This here states that if the page is running the seconds must increment
                {
                    seconds++ //incrementation
                }
                handler.postDelayed(this, 1000) //Delayed by  a 1000 of second.
            }
        })
    }

    companion object {
        var pausedTime: String? = null
    }

}