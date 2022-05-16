package com.jessica.yourfavoritemovies.favorites.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jessica.yourfavoritemovies.adapter.MovieAdapter
import com.jessica.yourfavoritemovies.R
import com.jessica.yourfavoritemovies.databinding.ActivityFavoritesBinding
import com.jessica.yourfavoritemovies.favorites.viewmodel.FavoriteViewModel
import com.jessica.yourfavoritemovies.data.remote.model.Result

class FavoritesActivity : AppCompatActivity() {
    private var resultRemove = Result()
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(
            ArrayList(), this::removeFavoriteMovie
        )
    }

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = resources.getString(R.string.action_favoritos)
        binding.rvMoviesFavorites.adapter = adapter
        binding.rvMoviesFavorites.layoutManager = LinearLayoutManager(this)
        viewModel.getFavorites()
        initViewModel()
    }

    private fun removeFavoriteMovie(result: Result){
        viewModel.removeFavoriteClickListener(result)
    }

    private fun initViewModel() {
        viewModel.stateList.observe(this) { state ->
            state?.let {
                showListFavorites(it as MutableList<Result>)
            }
        }

        viewModel.stateRemoveFavorite.observe(this) { favorite ->
            favorite?.let {
                showMessageRemovedFavorite(it)
            }

        }
    }

    private fun showListFavorites(list: MutableList<Result>){
        adapter.removeItem(resultRemove)
        adapter.updateList(list)
    }

    private fun showMessageRemovedFavorite(result: Result){
        resultRemove = result
        Snackbar.make(
            binding.rvMoviesFavorites,
             resources.getString(R.string.removed_movie, result.title),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}