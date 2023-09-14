package com.example.c3atimetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c3atimetracker.R
import java.text.SimpleDateFormat
import java.util.*

class CalculateActivity : AppCompatActivity() {


    private lateinit var startTextDate: EditText
    private lateinit var endTextDate: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultRecyclerView: RecyclerView
    private lateinit var resultAdapter: ResultAdapter
    private lateinit var totalHoursTextView: TextView
    private lateinit var showGraphButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate)


        startTextDate = findViewById(R.id.startTextDate)
        endTextDate = findViewById(R.id.endTextDate)
        calculateButton = findViewById(R.id.CalculateButton)
        resultRecyclerView = findViewById(R.id.resultRecyclerView)
        totalHoursTextView = findViewById(R.id.totalHoursTextView)
        showGraphButton = findViewById(R.id.showGraphButton)
        showGraphButton.setOnClickListener {
            var totalHours = totalHoursTextView.text.toString().toInt()
            openGraphActivity(totalHours)
        }

        resultAdapter = ResultAdapter()
        resultRecyclerView.adapter = resultAdapter
        resultRecyclerView.layoutManager = LinearLayoutManager(this)

        calculateButton.setOnClickListener {
            calculateTotalHours()
        }
    }

    private fun calculateTotalHours() {
        val startTime = startTextDate.text.toString().trim()
        val endTime = endTextDate.text.toString().trim()

        if (startTime.isNotEmpty() && endTime.isNotEmpty()) {
            val totalHours = calculateHours(startTime, endTime)
            displayTotalHours(totalHours)
            showGraphButton.isEnabled = true
        } else {
            totalHoursTextView.text = ""
            showGraphButton.isEnabled = false
        }
    }

    private fun calculateHours(startTime: String, endTime: String): Int {
        val timePattern = "HH:mm"
        val timeFormat = SimpleDateFormat(timePattern, Locale.getDefault())
        timeFormat.isLenient = false

        return try {
            val start = timeFormat.parse(startTime)
            val end = timeFormat.parse(endTime)

            val startCalendar = Calendar.getInstance().apply { time = start }
            val endCalendar = Calendar.getInstance().apply { time = end }

            val totalMilliseconds = endCalendar.timeInMillis - startCalendar.timeInMillis
            val totalSeconds = totalMilliseconds / 1000
            val totalMinutes = totalSeconds / 60
            val totalHours = totalMinutes / 60

            totalHours.toInt()
        } catch (e: Exception) {
            Log.e("CalculateActivity", "Exception: ${e.message}")
            e.printStackTrace()
            0
        }
    }

    private fun displayTotalHours(totalHours: Int) {
        totalHoursTextView.text = (totalHours).toString()
    }

    private fun openGraphActivity(totalHours: Int) {
        val intent = Intent(this, GraphActivity::class.java)
        intent.putExtra("totalHours", totalHours)
        startActivity(intent)
    }
}


