package com.example.c3atimetracker

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.c3atimetracker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.toolbar)

        val cat: CardView = findViewById<CardView>(R.id.cat)
        val log: CardView = findViewById<CardView>(R.id.log)
        val chat: CardView = findViewById<CardView>(R.id.chat)
        val report: CardView = findViewById<CardView>(R.id.report)
        val imageView3: CardView = findViewById<CardView>(R.id.time_sheet)
        val timesheet: CardView = findViewById<CardView>(R.id.time_sheet)

       val  calender: CardView = findViewById<CardView>(R.id.calender)
        val drawerLayout: DrawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        setSupportActionBar(toolbar);
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()



        cat.setOnClickListener {

            val intent = Intent(this, categories::class.java);
            startActivity(intent)
        }

        log.setOnClickListener {

            val intent = Intent(this, logtime::class.java);
            startActivity(intent)
        }

        timesheet.setOnClickListener {

            val intent = Intent(this, com.example.c3atimetracker.timesheet::class.java);
            startActivity(intent)


        }
        report.setOnClickListener{

            val intent = Intent(this, com.example.c3atimetracker.report::class.java);
            startActivity(intent)
        }

        calender.setOnClickListener{

            val intent = Intent(this, com.example.c3atimetracker.calender::class.java);
            startActivity(intent)
        }


    }
}
