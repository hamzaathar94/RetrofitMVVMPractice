package com.example.retrofitmvvmpractice.remote

import com.example.retrofitmvvmpractice.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceImp() {
    companion object{
        val retroFitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

    }
}