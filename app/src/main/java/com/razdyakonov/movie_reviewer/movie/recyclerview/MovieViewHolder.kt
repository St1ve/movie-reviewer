package com.razdyakonov.movie_reviewer.movie.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.razdyakonov.movie_reviewer.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.movie_name_tv)
}
