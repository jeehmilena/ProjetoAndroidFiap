package com.jessica.yourfavoritemovies.data.remote.datasource

import com.jessica.yourfavoritemovies.data.remote.api.MovieAPI
import com.jessica.yourfavoritemovies.data.remote.mapper.moviePayloadMapper
import com.jessica.yourfavoritemovies.domain.model.Movie
import com.jessica.yourfavoritemovies.util.Constants.API_KEY
import com.jessica.yourfavoritemovies.util.Constants.LANGUAGE_PT_BR

class MovieRemoteDataSourceImpl(
    private val movieAPI: MovieAPI
): MovieRemoteDataSource {
    override suspend fun getMovies(): List<Movie> {
        return movieAPI.getMovie(API_KEY, LANGUAGE_PT_BR).moviePayloadMapper()
    }
}