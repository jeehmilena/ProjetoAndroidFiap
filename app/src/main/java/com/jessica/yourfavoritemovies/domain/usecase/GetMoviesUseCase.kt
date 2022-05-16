package com.jessica.yourfavoritemovies.domain.usecase

import com.jessica.yourfavoritemovies.domain.model.Movie
import com.jessica.yourfavoritemovies.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend fun getMovies(): List<Movie> {
        return movieRepository.getMovies()
    }
}