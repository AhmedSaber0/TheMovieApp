package com.route.movieapplication.repo

import androidx.lifecycle.MutableLiveData
import com.route.movieapplication.common.API_KEY
import com.route.movieapplication.data.remote.RetrofitClient
import com.route.movieapplication.models.details.MovieDetailsResponse
import com.route.movieapplication.models.popular.PopularResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    fun getPopularMovies(): MutableLiveData<PopularResponse> {
        val newsSourcesLiveData = MutableLiveData<PopularResponse>()
        RetrofitClient.apiService.getPopularMovies(API_KEY, page = 1)
            .enqueue(object : Callback<PopularResponse> {
                override fun onFailure(call: Call<PopularResponse>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<PopularResponse>,
                    response: Response<PopularResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            newsSourcesLiveData.postValue(it)
                        }
                    }
                }

            })
        return newsSourcesLiveData
    }

    fun getMovieDetails(movieId: Int) = RetrofitClient.apiService.getMovieDetails(movieId, API_KEY)

}