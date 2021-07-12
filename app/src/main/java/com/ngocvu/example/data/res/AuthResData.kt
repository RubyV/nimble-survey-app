package com.ngocvu.example.data.res

import com.google.gson.annotations.SerializedName

sealed class AuthResData {

    data class Res(
        val data: Data
    )

    data class Data(
        val attributes: Attributes,
        val id: Int,
        val type: String
    )

    data class Attributes(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("created_at")
        val createdAt: Int,
        @SerializedName("expires_in")
        val expiresIn: Int,
        @SerializedName("refresh_token")
        val refreshToken: String,
        @SerializedName("token_type")
        val tokenType: String
    )
}