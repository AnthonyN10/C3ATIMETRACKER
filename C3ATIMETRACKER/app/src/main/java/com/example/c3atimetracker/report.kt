package com.example.c3atimetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.util.ArrayList

@Suppress("DEPRECATION")
class report : AppCompatActivity() {

    // on below line we are creating
    // variables for our bar chart
    lateinit var barChart: BarChart


    // on below line we are creating
    // a variable for bar data set
    lateinit var barDataSet1: BarDataSet
    lateinit var barDataSet2: BarDataSet
    lateinit var barDataSet3: BarDataSet 

    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>

    // creating a string array for displaying days.
    var days = arrayOf("Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val minGoal = intent.getStringExtra("MinGoal")
        val maxGoal = intent.getStringExtra("MaxGoal")



        // on below line we are initializing
        // our variable with their ids.
        barChart = findViewById(R.id.idBarChart)

        // on below line we are creating a new bar data set
        barDataSet1 = BarDataSet(getBarChartDataForSet1(minGoal), "Min Hours")
        barDataSet1.setColor(resources.getColor(R.color.purple_200))
        barDataSet2 = BarDataSet(getBarChartDataForSet2(), "Hours Worked")
        barDataSet2.setColor(resources.getColor(R.color.teal_200))
        barDataSet3 = BarDataSet(getBarChartDataForSet3(maxGoal), "Max Hours")
        barDataSet3.setColor(resources.getColor(R.color.green))


        val buttonRedirect = findViewById<Button>(R.id.movepage)
        buttonRedirect.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity (intent)
        }

        // on below line we are adding bar data set to bar data
        val data = BarData(barDataSet1, barDataSet2, barDataSet3)

        // on below line we are setting data to our chart
        barChart.data = data

        // on below line we are setting description enabled.
        barChart.description.isEnabled = false

        // on below line setting x axis
        val xAxis = barChart.xAxis

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.valueFormatter = IndexAxisValueFormatter(days)

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(true)

        // below line is to set position
        // to our x-axis to bottom.
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // below line is to set granularity
        // to our x axis labels.
        xAxis.setGranularity(1f)

        // below line is to enable
        // granularity to our x axis.
        xAxis.setGranularityEnabled(true)

        // below line is to make our
        // bar chart as draggable.
        barChart.setDragEnabled(true)

        // below line is to make visible
        // range for our bar chart.
        barChart.setVisibleXRangeMaximum(3f)

        // below line is to add bar
        // space to our chart.
        val barSpace = 0.1f

        // below line is use to add group
        // spacing to our bar chart.
        val groupSpace = 0.5f

        // we are setting width of
        // bar in below line.
        data.barWidth = 0.25f

        // below line is to set minimum
        // axis to our chart.
        barChart.xAxis.axisMinimum = 0f

        // below line is to
        // animate our chart.
        barChart.animate()

        // below line is to group bars
        // and add spacing to it.
        barChart.groupBars(0f, groupSpace, barSpace)

        // below line is to invalidate
        // our bar chart.
        barChart.invalidate()


    }

    private fun getBarChartDataForSet1(minGoal : String?): ArrayList<BarEntry> {
        barEntriesList = ArrayList()
        val firstValue = minGoal?.toFloatOrNull() ?: 0f

        // on below line we are adding
        // data to our bar entries list
        barEntriesList.add(BarEntry(1f, firstValue))
        barEntriesList.add(BarEntry(2f, firstValue))
        barEntriesList.add(BarEntry(3f, firstValue))
        barEntriesList.add(BarEntry(4f, firstValue))
        barEntriesList.add(BarEntry(5f, firstValue))
        barEntriesList.add(BarEntry(6f, firstValue))
        barEntriesList.add(BarEntry(7f, firstValue))
        return barEntriesList
    }




    private fun getBarChartDataForSet2(): ArrayList<BarEntry> {
        barEntriesList = ArrayList()
        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 7f))
        barEntriesList.add(BarEntry(2f, 0f))
        barEntriesList.add(BarEntry(3f, 0f))
        barEntriesList.add(BarEntry(4f, 0f))
        barEntriesList.add(BarEntry(5f, 0f))
        barEntriesList.add(BarEntry(6f, 0f))
        barEntriesList.add(BarEntry(7f, 0f))
        return barEntriesList
    }

    private fun getBarChartDataForSet3(maxGoal: String?): ArrayList<BarEntry> {
        barEntriesList = ArrayList()
        val maxValue = maxGoal?.toFloatOrNull() ?: 0f
        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, maxValue))
        barEntriesList.add(BarEntry(2f, maxValue))
        barEntriesList.add(BarEntry(3f, maxValue))
        barEntriesList.add(BarEntry(4f, maxValue))
        barEntriesList.add(BarEntry(5f, maxValue))
        barEntriesList.add(BarEntry(6f, maxValue))
        barEntriesList.add(BarEntry(7f, maxValue))
        return barEntriesList
    }


}