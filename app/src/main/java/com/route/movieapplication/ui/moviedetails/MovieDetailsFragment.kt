package com.route.movieapplication.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.route.movieapplication.R
import com.route.movieapplication.common.MOVIEDB_IMAGE_BASE
import com.route.movieapplication.models.details.MovieDetailsResponse
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {

    private lateinit var subscriptions: Disposable
    private lateinit var genersAdapter: GenersAdapter
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    val arguments: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieDetailsViewModel =
            ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

    }

    private fun setupRecyclerView() {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        genersAdapter = GenersAdapter { view, genresItem, i ->
        }
        genersRecyclerView.layoutManager = layoutManager
        genersRecyclerView.adapter = genersAdapter
    }

    private fun setupObservers() {
        subscriptions = movieDetailsViewModel.getMovieDetails(arguments.movieId).subscribe(
            { response ->
                setupViews(response)
            },
            {
                Toast.makeText(requireContext(), "error occurred", Toast.LENGTH_LONG).show()
            }
        )
//
//        subscription = movieDetailsViewModel.getMovieDetails(arguments.movieId).subscribe(
//            { response ->
//                setupViews(response)
//            },
//            {}
//        )
//
//        movieDetailsViewModel.getMovieDetails(arguments.movieId)
//            .observe(viewLifecycleOwner, Observer {
//
//            })
    }

    private fun setupViews(data: MovieDetailsResponse?) {
        data?.let {
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${it.posterPath}").into(posterImageView)
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${it.backdropPath}").into(bannerImageView)

            genersAdapter.swapData(it.genres)

            titleTextView.text = it.title
            overviewTextView.text = it.overview
            ratingTextView.text = it.voteAverage.toString()
            languageTextView.text = it.originalLanguage
            releaseDateTextView.text = it.releaseDate
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (subscriptions.isDisposed)
            subscriptions.dispose()
    }
}
