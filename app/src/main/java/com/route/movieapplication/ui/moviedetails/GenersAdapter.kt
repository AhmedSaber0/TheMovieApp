package com.route.movieapplication.ui.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.movieapplication.R
import com.route.movieapplication.models.details.GenresItem
import kotlinx.android.synthetic.main.layut_generes_item.view.*
import java.util.*

class GenersAdapter(val listener: (View, GenresItem, Int) -> Unit) :
    RecyclerView.Adapter<GenersAdapter.GeneresViewHolder>() {

    private var data: List<GenresItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneresViewHolder {
        return GeneresViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layut_generes_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: GeneresViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<GenresItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class GeneresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: GenresItem) = with(itemView) {
            nameTextView.text = item.name
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}