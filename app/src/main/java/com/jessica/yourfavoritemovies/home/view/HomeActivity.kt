package com.jessica.yourfavoritemovies.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import com.jessica.yourfavoritemovies.util.Constants.LANGUAGE_PT_BR
import com.jessica.yourfavoritemovies.R
import com.jessica.yourfavoritemovies.about.view.AboutActivity
import com.jessica.yourfavoritemovies.adapter.MovieAdapter
import com.jessica.yourfavoritemovies.authentication.view.LoginActivity
import com.jessica.yourfavoritemovies.databinding.ActivityHomeBinding
import com.jessica.yourfavoritemovies.favorites.view.FavoritesActivity
import com.jessica.yourfavoritemovies.home.viewmodel.HomeViewModel
import com.jessica.yourfavoritemovies.model.Result

class HomeActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(
            ArrayList(), this::favoriteMovie
        )
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        initViewModel()
        viewModel.getListMovies(LANGUAGE_PT_BR)
    }

    private fun initViewModel() {
        viewModel.stateList.observe(this) { state ->
            state?.let {
                showListMovies(it as MutableList<Result>)
            }
        }

        viewModel.stateFavorite.observe(this) { favorite ->
            favorite?.let {
                showMessageFavorite(it)
            }

        }

        viewModel.loading.observe(this) { loading ->
            loading?.let {
                showLoading(it)
            }
        }

        viewModel.error.observe(this) { loading ->
            loading?.let {
                showErrorMessage(it)
            }
        }
    }

    private fun showListMovies(list: MutableList<Result>) {
        adapter.updateList(list)
    }

    private fun favoriteMovie(result: Result) {
        viewModel.saveFavorite(result)
    }

    private fun showMessageFavorite(result: Result) {
        Snackbar.make(
            binding.rvMovies,
            resources.getString(R.string.added_movie, result.title),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showLoading(status: Boolean) {
        when {
            status -> {
                binding.pbMovies.visibility = View.VISIBLE
            }
            else -> {
                binding.pbMovies.visibility = View.GONE
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.rvMovies, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_favoritos) {
            startActivity(Intent(this, FavoritesActivity::class.java))
            return true
        }
        if (id == R.id.action_about) {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }
        if (id == R.id.action_logout) {
            logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    )
                )
                finish()
            }
    }
}