package com.mg.goldenmarc.data.remote.network

import com.mg.goldenmarc.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiDbClient {

    private val client = OkHttpClient.Builder().build()
    private val baseUrl: String = R.string.api_url.toString()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: ApiService = retrofit.create(ApiService::class.java)

}

