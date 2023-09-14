package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView


class ViewcategoriesaActivity : AppCompatActivity() {


    private lateinit var spinner: Spinner
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewcategoriesactivity)


        var itemList1 = intent.getStringArrayListExtra("itemList") ?: ArrayList()
        spinner = findViewById(R.id.spinner)

        // Retrieve the item list from the intent

        // Create an ArrayAdapter for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        fun goToActivityC(view: View) {
            val intent = Intent(this, timesheet::class.java)
            startActivity(intent)
        }
    }
}

