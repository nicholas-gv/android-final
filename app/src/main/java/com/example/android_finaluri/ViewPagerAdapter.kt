package com.example.android_finaluri

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(val texts: List<String?>): RecyclerView.Adapter<ViewPagerAdapter.ViewPageHolder>() {
    inner class ViewPageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val infoText: TextView = itemView.findViewById(R.id.infoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageHolder {
        Log.d("eerorte", "ewqeweqw")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.info_view_pager, parent, false)
        return ViewPageHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPageHolder, position: Int) {
        holder.infoText.text = texts[position].toString()
    }

    override fun getItemCount(): Int {
        return texts.size
    }
}