
package com.example.c3atimetracker
import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.c3atimetracker.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter


class GraphActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var totalHoursTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.graph)

        barChart = findViewById(R.id.barChart)
        totalHoursTextView = findViewById(R.id.totalHoursTextView)

        val totalHours = intent.getIntExtra("totalHours",0 )
        totalHoursTextView.text = "Total Hours: $totalHours"

        setupBarChart()
        generateBarChartData(totalHours)
    }

    private fun setupBarChart() {
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.description.isEnabled = false
        barChart.setMaxVisibleValueCount(60)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)

        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)

        val yAxis = barChart.axisLeft
        yAxis.setLabelCount(5, false)
        yAxis.setDrawGridLines(false)
        yAxis.axisMinimum = 0f

        barChart.axisRight.isEnabled = false
    }

    private fun generateBarChartData(totalHours: Int) {
        val barEntries = ArrayList<BarEntry>()
        barEntries.add(BarEntry(0f, totalHours.toFloat()))

        val barDataSet = BarDataSet(barEntries, "Total Hours")
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS)
        barDataSet.valueTextColor = android.R.color.black

        val dataSets = ArrayList<IBarDataSet>()
        dataSets.add(barDataSet)

        val barData = BarData(dataSets)
        barData.barWidth = 0.9f

        barChart.data = barData
        barChart.invalidate()
    }
}

private fun BarDataSet.setColors(colors: IntArray?) {

}
