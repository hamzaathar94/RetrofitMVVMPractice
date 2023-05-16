package com.example.retrofitmvvmpractice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitmvvmpractice.adapter.MoviesAdapter
import com.example.retrofitmvvmpractice.databinding.ActivityMainBinding
import com.example.retrofitmvvmpractice.model.MovieItem
import com.example.retrofitmvvmpractice.remote.APIServiceImp
import com.example.retrofitmvvmpractice.repository.MoviesRepository
import com.example.retrofitmvvmpractice.viewmodel.MoviesViewModel
import com.example.retrofitmvvmpractice.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    private var myViewModel : MoviesViewModel?=null
    private var adapter : MoviesAdapter?=null
    private var moviesList = mutableListOf<MovieItem>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        val repository = MoviesRepository(APIServiceImp.retroFitInstance)
        val viewModelFactory = ViewModelFactory(repository)
        myViewModel = ViewModelProvider(this,viewModelFactory)[MoviesViewModel::class.java]
        CoroutineScope(Dispatchers.IO).launch {
            myViewModel?.getMoviesObserver()
        }
        myViewModel?.moviesObserver?.observe(this, Observer {
            it.forEach {movie->
                moviesList.add(movie)
            }
            adapter?.notifyDataSetChanged()
        })
        adapter = MoviesAdapter(moviesList)
        binding?.recyclerViewMovies?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding?.recyclerViewMovies?.adapter = adapter
    }
}