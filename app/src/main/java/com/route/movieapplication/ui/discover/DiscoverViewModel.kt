package com.route.movieapplication.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.movieapplication.repo.MovieRepository

class DiscoverViewModel : ViewModel() {

    val movieRepository = MovieRepository()

    fun getPopularMovies() = movieRepository.getPopularMovies()
}