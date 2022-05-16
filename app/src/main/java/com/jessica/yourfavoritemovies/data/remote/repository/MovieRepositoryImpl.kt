package com.jessica.yourfavoritemovies.data.remote.repository

import com.jessica.yourfavoritemovies.data.remote.datasource.MovieRemoteDataSource
import com.jessica.yourfavoritemovies.domain.model.Movie
import com.jessica.yourfavoritemovies.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
    ): MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return movieRemoteDataSource.getMovies()
    }
}