package com.jessica.yourfavoritemovies.datasource

import com.jessica.yourfavoritemovies.util.Constants.API_KEY

class MovieRepository {
    suspend fun getMovies(language: String) = MovieService.getApi().getApodDay(API_KEY, language)
}