package com.example.testmovieapiusingcall.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MovieResponse: ArrayList<MovieItem>()

@Parcelize
data class MovieItem(
   val title :String,
   val image: String,
   val rating : Double,
   val releaseYear : Int,
   val genre : List<String>
): Parcelable