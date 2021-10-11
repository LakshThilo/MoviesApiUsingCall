package com.example.testmovieapiusingcall.view.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmovieapiusingcall.model.MovieItem
import com.example.testmovieapiusingcall.databinding.DisplayMovieItemBinding

class MovieViewHolder(private val binding: DisplayMovieItemBinding,
private val click: (MovieItem)-> Unit): RecyclerView.ViewHolder(binding.root) {

    fun onBind(movieItem: MovieItem) {

        binding.tvMovieTitle.text = movieItem.title
        binding.tvMovieRating.text = movieItem.rating.toString()
        binding.tvMovieGenre.text = movieItem.releaseYear.toString()

        Glide.with(itemView.context)
            .load(movieItem.image)
            .into(binding.ivMovieImage)


        binding.root.setOnClickListener{click(movieItem)}

    }


}