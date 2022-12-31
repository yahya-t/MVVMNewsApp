package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    // @GET call to get the URL - this example get the "top-headlines"
    // (use the APIs documentation to get the URLs that can make requests)
    // this is a network call function so it needs to be a suspended function to use coroutines
    // the @Query parameter is used to retrieve
    // API_KEY is a constant from the Constants class

    /**
     * Gets the top headlines
     *
     * @param countryCode the country code retrieved from the News API - set by default to "us"
     * @param pageNumber to paginate requests for a certain number of articles, instead of requesting all at once
     * @param apiKey the apiKey to let NewsAPI know who makes the request
     */
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    /**
     * Gets the searched headlines
     *
     * @param searchQuery to search using String
     * @param pageNumber to paginate requests for a certain number of articles, instead of requesting all at once
     * @param apiKey the apiKey to let NewsAPI know who makes the request
     */
    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}