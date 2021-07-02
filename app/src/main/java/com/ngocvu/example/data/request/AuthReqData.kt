package com.ngocvu.example.data.request

import com.google.gson.annotations.SerializedName

data class AuthReqData (
    @SerializedName("grant_type")
    val grant_type: String = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("client_id")
    val client_id: String,
    @SerializedName("client_secret")
    val client_secret: String
    )