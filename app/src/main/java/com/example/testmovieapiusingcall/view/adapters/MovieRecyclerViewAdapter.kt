package com.example.testmovieapiusingcall.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testmovieapiusingcall.model.MovieItem
import com.example.testmovieapiusingcall.view.viewholders.MovieViewHolder
import com.example.testmovieapiusingcall.databinding.DisplayMovieItemBinding

private const val TAG = "Movie RecyclerView"
class MovieRecyclerViewAdapter(var dataSet: List<MovieItem>, private val clickDetails:(MovieItem) -> Unit ):
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //Log.d(TAG, "onCreateViewHolder: $dataSet")
        return MovieViewHolder(
            DisplayMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickDetails
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.onBind(dataSet[position])

    }


    override fun getItemCount() = dataSet.size
}