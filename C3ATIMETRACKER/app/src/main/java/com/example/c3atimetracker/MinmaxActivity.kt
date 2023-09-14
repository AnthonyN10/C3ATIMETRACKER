package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

open class MinmaxActivity : AppCompatActivity() {
    open lateinit var MinGoal:String
    open lateinit var MaxGoal:String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minmax)

        val editTextMin = findViewById<EditText>(R.id.editTextNumber1)
        val editTextMax = findViewById<EditText>(R.id.editTextNumber2)
        val buttonLoad = findViewById<Button>(R.id.btnLoad)

        buttonLoad.setOnClickListener {
            MinGoal = editTextMin.text.toString()
            MaxGoal = editTextMax.text.toString()

            if (MinGoal.isEmpty() || MaxGoal.isEmpty()) {
                Toast.makeText(this, "Please enter the Min and Max Goals", Toast.LENGTH_SHORT)
                    .show()
            } else if (MinGoal >= 1.toString() || MaxGoal <= 12.toString()) {
                val buttonRedirect = findViewById<Button>(R.id.btnLoad)
                buttonRedirect.setOnClickListener {
                    val intent = Intent(this, report::class.java)
                    intent.putExtra("MinGoal", MinGoal)
                    intent.putExtra("MaxGoal", MaxGoal)
                    startActivity(intent)
                    Toast.makeText(this, "Goals Saved", Toast.LENGTH_SHORT).show()
                }


            }
        }


    }

}

