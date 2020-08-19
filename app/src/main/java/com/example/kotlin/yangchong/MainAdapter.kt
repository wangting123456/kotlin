package com.example.kotlin.yangchong

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R


internal  class MainAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvName: TextView
    init {
       tvName = itemView.findViewById(R.id.recycleView)
    }

}