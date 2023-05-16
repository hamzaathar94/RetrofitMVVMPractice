package com.example.retrofitmvvmpractice.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitmvvmpractice.model.MovieItem
import com.example.retrofitmvvmpractice.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesRepository: MoviesRepository):ViewModel() {
    val moviesObserver = MutableLiveData<List<MovieItem>>()

    suspend fun getMoviesObserver(){
       val response = moviesRepository.getMoviesList()
        if(response.isSuccessful){
            withContext(Dispatchers.Main){
            moviesObserver.value = response.body()
            }
        }  else {
         Log.d("checkError: ",response.message())
        }
    }
}