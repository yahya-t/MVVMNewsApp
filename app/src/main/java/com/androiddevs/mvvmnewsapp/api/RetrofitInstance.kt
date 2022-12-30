package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/** Retrofit singleton class used to make network requests */
class RetrofitInstance {

    companion object {

        /** Builds the Retrofit object */
        private val retrofit by lazy {
            // log responses of Retrofit (for debugging) by creating an interceptor
            val logging = HttpLoggingInterceptor()

            //attach this to the Retrofit object to see requests and logg responses
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) //see the BODY of the response

            // use the interceptor to create a network client
            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            // use the client to attach to Retrofit instance
            // addConverterFactory() determines how responses are interpreted and converted to Kotlin objects
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        }

        // get api instance from the Retrofit Builder
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}