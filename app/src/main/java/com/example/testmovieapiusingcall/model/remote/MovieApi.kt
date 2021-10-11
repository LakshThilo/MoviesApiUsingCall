package com.example.testmovieapiusingcall.model.remote

import com.example.testmovieapiusingcall.common.Constants
import com.example.testmovieapiusingcall.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {

    @GET(Constants.END_POINT)
    fun getMovies(): Call<MovieResponse>
}