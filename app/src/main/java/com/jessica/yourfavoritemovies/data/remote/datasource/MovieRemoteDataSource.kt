package com.jessica.yourfavoritemovies.data.remote.datasource

import com.jessica.yourfavoritemovies.domain.model.Movie

interface MovieRemoteDataSource {
    suspend fun getMovies() : List<Movie>
}