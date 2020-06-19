package com.route.movieapplication.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.route.movieapplication.R
import kotlinx.android.synthetic.main.fragment_home.*

class DiscoverFragment : Fragment() {

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var discoverViewModel: DiscoverViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }
    private fun setupObservers() {
        discoverViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            if (it.results.isNotEmpty())
                popularMoviesAdapter.swapData(it.results)
        })
    }

    private fun setupRecyclerView() {
        popularMoviesAdapter = PopularMoviesAdapter { view, resultsItem, i ->
            val action = DiscoverFragmentDirections.actionNavigationDiscoverToNavigationMovieDetails(
                resultsItem.id
            )
            findNavController().navigate(action)
        }
        moviesRecyclerView.adapter = popularMoviesAdapter
    }
}
