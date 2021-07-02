package com.ngocvu.example.data.request

import com.google.gson.annotations.SerializedName

data class RefreshTokenReqData (
    @SerializedName("grant_type")
    val grant_type: String = "",
    @SerializedName("refresh_token")
    val refresh_token: String? = "",
    @SerializedName("client_id")
    val client_id: String,
    @SerializedName("client_secret")
    val client_secret: String
)