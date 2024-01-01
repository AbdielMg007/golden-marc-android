package com.mg.goldenmarc.data.remote.network

import com.google.gson.Gson
import com.mg.goldenmarc.data.model.UserCredentialsLogin
import com.mg.goldenmarc.data.model.UserResponseErrorLogin
import com.mg.goldenmarc.data.model.UserResponseLogin
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {

    suspend fun login(username: String, password: String): Response<UserResponseLogin> {
        val request = UserCredentialsLogin(username, password)
        return apiService.login(request)
    }

    suspend fun handleErrorResponse(response: Response<*>, gson: Gson): String? {
        return try {
            gson.fromJson(response.errorBody()?.charStream(), UserResponseErrorLogin::class.java)?.error
        } catch (e: Exception) {
            null
        }
    }
}
