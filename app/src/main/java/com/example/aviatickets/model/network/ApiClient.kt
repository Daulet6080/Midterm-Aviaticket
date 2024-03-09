package com.example.aviatickets.model.network


import com.example.aviatickets.model.entity.Offer
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object ApiClient {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/estharossa/fake-api-demo/") // Make sure to replace "YOUR_BASE_URL" with your actual base URL
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getApiService(): ApiService = retrofit.create(ApiService::class.java)
}

interface ApiService {
    @GET("offer_list")
    fun getOffers(): Call<List<Offer>>
}