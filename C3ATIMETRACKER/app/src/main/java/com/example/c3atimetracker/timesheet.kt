package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.cardview.widget.CardView
import com.google.firebase.database.*

class timesheet : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var editTextDate: EditText
    private lateinit var editstartTime: EditText
    private lateinit var endtimetxt: EditText
    private lateinit var descrText: EditText
    private lateinit var savebutton: Button
    private lateinit var viewbtn : Button
    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)

        database = FirebaseDatabase.getInstance().getReference("Categories")

        spinner = findViewById(R.id.spinner)
        val createcattext: Button = findViewById<Button>(R.id.createcattext)
        val imagebtn: Button = findViewById<Button>(R.id.imagebtn)
        val viewButton: Button = findViewById(R.id.viewbtn)

        editTextDate = findViewById(R.id.editTextDate)
        editstartTime = findViewById(R.id.editstartTime)
        endtimetxt = findViewById(R.id.endtimetxt)
        descrText = findViewById(R.id.descrText)
        savebutton = findViewById(R.id.savebutton)
        viewbtn =findViewById(R.id.viewbtn)

        val itemList1 = ArrayList<String>()

        createcattext.setOnClickListener {
            val intent = Intent(this, categories::class.java)
            startActivity(intent)
        }

        imagebtn.setOnClickListener{
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }

        savebutton.setOnClickListener {
            val date = editTextDate.text.toString()
            val startTime = editstartTime.text.toString()
            val endTime = endtimetxt.text.toString()
            val description = descrText.text.toString()

            val event = Event(date, startTime, endTime, description)
            EventDataSource.addEvent(event)

            Toast.makeText(this, "Entries saved!", Toast.LENGTH_SHORT).show()
            clearFields()
        }

        viewbtn.setOnClickListener {
            val intent = Intent(this, ViewtimesheetActivity::class.java)
            startActivity(intent)
        }

        loadCategories()

        // Create an ArrayAdapter for the spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList1)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
    private fun loadCategories() {
        database.child("Categories").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val itemList = ArrayList<String>()
                for (childSnapshot in dataSnapshot.children) {
                    val category = childSnapshot.key as String
                    category?.let { itemList.add(it) }
                }

                populateSpinnerWithHardcodedNames()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
                Log.e("TAG", "Failed to retrieve categories: ${databaseError.message}")
            }
        })
    }


    private fun populateSpinnerWithHardcodedNames() {
        val Names = listOf("IPMA", "PROG", "OPSC")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Names)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }


    private fun clearFields() {
        editTextDate.text.clear()
        editstartTime.text.clear()
        endtimetxt.text.clear()
        descrText.text.clear()
    }
}
