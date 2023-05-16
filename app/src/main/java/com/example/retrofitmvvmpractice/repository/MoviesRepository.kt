package com.example.retrofitmvvmpractice.repository

import com.example.retrofitmvvmpractice.remote.APIService

class MoviesRepository(private val apiServiceImp: APIService) {

    suspend fun getMoviesList() = apiServiceImp.getMoviesList()
}