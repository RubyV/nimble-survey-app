package com.ngocvu.example.data.request

import com.google.gson.annotations.SerializedName

data class AuthReqData (
    @SerializedName("grant_type")
    val grantType: String = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String
    )