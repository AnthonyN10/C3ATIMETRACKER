package com.example.c3atimetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.c3atimetracker.R

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private val resultList: MutableList<String> = mutableListOf()

    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val resultTextView: TextView = itemView.findViewById(R.id.resultTextView)

        fun bind(result: String) {
            resultTextView.text = result
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = resultList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    fun addResult(result: String) {
        resultList.add(result)
        notifyItemInserted(resultList.size - 1)
    }
}
