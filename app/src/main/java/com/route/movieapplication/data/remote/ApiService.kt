package com.route.movieapplication.data.remote

import com.route.movieapplication.models.details.MovieDetailsResponse
import com.route.movieapplication.models.popular.PopularResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular/sss")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("region") region: String? = null
    ): Call<PopularResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailsResponse>

}