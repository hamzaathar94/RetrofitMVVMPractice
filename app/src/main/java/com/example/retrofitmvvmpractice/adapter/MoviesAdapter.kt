package com.example.retrofitmvvmpractice.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.retrofitmvvmpractice.databinding.RvMovieItemViewBinding
import com.example.retrofitmvvmpractice.model.MovieItem

class MoviesAdapter (private val listOfMovies : List<MovieItem>): RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RvMovieItemViewBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = RvMovieItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.itemTitleTv.text = listOfMovies[position].name
        val circularProgressDrawable = CircularProgressDrawable(holder.binding.root.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide.with(holder.binding.root.context)
            .load(listOfMovies[position].imageurl)
            .placeholder(circularProgressDrawable)
            .into(holder.binding.itemImageView)

      //  Log.d("ooo",listOfMovies[position].bio)
    }
}