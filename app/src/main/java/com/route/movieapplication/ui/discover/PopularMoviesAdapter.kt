package com.route.movieapplication.ui.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.movieapplication.R
import com.route.movieapplication.common.MOVIEDB_IMAGE_BASE
import com.route.movieapplication.models.popular.ResultsItem
import kotlinx.android.synthetic.main.layout_popular_movies_item.view.*
import java.util.*

class PopularMoviesAdapter(val listener: (View, ResultsItem, Int) -> Unit) :
    RecyclerView.Adapter<PopularMoviesAdapter.MoviesViewHolder>() {

    private var data: List<ResultsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_popular_movies_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<ResultsItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ResultsItem) = with(itemView) {
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${item.posterPath}")
                .into(movieImageView)
            nameTextView.text = item.title
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}