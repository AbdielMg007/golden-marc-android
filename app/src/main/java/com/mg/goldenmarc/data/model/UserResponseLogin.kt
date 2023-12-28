package com.mg.goldenmarc.data.model

data class UserResponseLogin(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var userName: String,
    var role: Int,
    var keyOffice: Int,
    var token: String?,
    var error: String?
)
