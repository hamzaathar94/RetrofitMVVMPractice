package com.example.retrofitmvvmpractice.remote

import com.example.retrofitmvvmpractice.model.MovieItem
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

   // @GET("homeMovie.json")
    @GET("marvel")
    suspend fun getMoviesList():Response<List<MovieItem>>
}