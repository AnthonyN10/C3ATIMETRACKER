package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class calender : AppCompatActivity() {

    private val calendarView: CalendarView? = null
    private val editText: EditText? = null

    private val stringDateSelected: String? = null

    private val databaseReference: DatabaseReference? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        var calendarView: CalendarView? = null
        var editText: EditText? = null

        var stringDateSelected: String? = null

        var databaseReference: DatabaseReference? = null


        calendarView = findViewById(R.id.calendarView)
        editText = findViewById<EditText>(R.id.editText)

        calendarView.setOnDateChangeListener(OnDateChangeListener { calendarView, i, i1, i2 ->
            stringDateSelected =
                Integer.toString(i) + Integer.toString(i1 + 1) + Integer.toString(i2)
            calendarClicked()
        })

        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar")


    }

    private fun calendarClicked() {
        if (stringDateSelected != null) {
            databaseReference?.child(stringDateSelected)
                ?.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value != null) {
                            editText?.setText(snapshot.value.toString())
                        } else {
                            editText?.setText("")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
        }
    }

    fun buttonSaveEvent(view: View?) {
        if (stringDateSelected != null) {
            databaseReference?.child(stringDateSelected)?.setValue(editText?.getText().toString())
        }
    }
}
