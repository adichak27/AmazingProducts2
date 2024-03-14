package com.cs4520.assignment4

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com/"
    // https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com/prod/?page=3
    val instance: ProductService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }
}