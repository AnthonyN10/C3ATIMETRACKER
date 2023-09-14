package com.example.c3atimetracker

import EventAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ViewtimesheetActivity : AppCompatActivity() , EventAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewtimesheet)

        recyclerView = findViewById(R.id.recyclerView)
        eventAdapter = EventAdapter(this)
        recyclerView.adapter = eventAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val events = EventDataSource.getEvents()
        eventAdapter.setEvents(events)
    }

    override fun onItemClick(event: Event) {
        val intent = Intent(this, CalculateActivity::class.java)
        intent.putExtra("event", event)
        startActivity(intent)
    }
}
