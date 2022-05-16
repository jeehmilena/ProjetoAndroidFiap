package com.jessica.yourfavoritemovies.data.remote.mapper

import com.jessica.yourfavoritemovies.data.remote.model.Movie
import com.jessica.yourfavoritemovies.data.remote.model.Result
import com.jessica.yourfavoritemovies.domain.model.Movie as MovieDomain

fun Movie.moviePayloadMapper(): List<MovieDomain> {
    return this.results.map {
        MovieDomain(
            imageURL = it.posterPath,
            title = it.title
        )
    }
}