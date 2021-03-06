package com.jessica.yourfavoritemovies.data

import com.google.gson.GsonBuilder
import com.jessica.yourfavoritemovies.BuildConfig
import com.jessica.yourfavoritemovies.data.remote.api.MovieAPI
import com.jessica.yourfavoritemovies.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    companion object {
        private val gson = GsonBuilder().setLenient().create()
        private val httpClient = OkHttpClient.Builder()

        init {
            val httpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
        }

        fun getApi(): MovieAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
                .create(MovieAPI::class.java)
        }
    }
}