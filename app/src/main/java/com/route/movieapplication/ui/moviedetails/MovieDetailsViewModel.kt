package com.route.movieapplication.ui.moviedetails

import androidx.lifecycle.ViewModel
import com.route.movieapplication.models.details.MovieDetailsResponse
import com.route.movieapplication.repo.MovieRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription

class MovieDetailsViewModel : ViewModel() {

    val movieRepository = MovieRepository()

    fun getMovieDetails(movieId: Int): Single<MovieDetailsResponse> {
        return movieRepository.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}