package com.mg.goldenmarc.data.remote.network

import com.mg.goldenmarc.data.model.UserCredentialsLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiClient {

    @POST("auth/login")
    suspend fun login(@Body request: UserCredentialsLogin): Response<UserCredentialsLogin>

}