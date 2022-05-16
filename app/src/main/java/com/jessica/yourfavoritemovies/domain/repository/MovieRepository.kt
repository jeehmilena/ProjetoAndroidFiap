package com.jessica.yourfavoritemovies.domain.repository

import com.jessica.yourfavoritemovies.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>
}