package com.jessica.yourfavoritemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jessica.yourfavoritemovies.R
import com.jessica.yourfavoritemovies.databinding.ItemRecyclerViewBinding
import com.jessica.yourfavoritemovies.data.remote.model.Result
import com.jessica.yourfavoritemovies.util.Constants.BASE_IMAGE_URL

class MovieAdapter(
    var listMovie: MutableList<Result>,
    val onClick: (item: Result) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listMovie[position])
        holder.binding.ivFavorite.setOnClickListener {
            listMovie[position].favorited = true
            holder.binding.ivFavorite.setImageDrawable(
                ResourcesCompat.getDrawable(
                    holder.itemView.resources,
                    R.drawable.ic_favorite,
                    null
                )
            )
            onClick(listMovie[position])
        }
    }

    override fun getItemCount(): Int = listMovie.size

    fun updateList(results: MutableList<Result>) {
        listMovie = results
        notifyDataSetChanged()
    }

    fun removeItem(result: Result) {
        listMovie.remove(result)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(result: Result) {
            binding.tvTitle.text = result.title

            Glide.with(itemView.context).load("$BASE_IMAGE_URL${result.posterPath}")
                .placeholder(R.mipmap.ic_movie)
                .into(binding.ivMovie)
        }
    }
}