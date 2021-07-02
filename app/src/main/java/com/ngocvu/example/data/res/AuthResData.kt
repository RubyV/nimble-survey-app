package com.ngocvu.example.data.res

sealed class AuthResData{

    data class Res(
        val data: Data
    )

    data class Data(
        val attributes: Attributes,
        val id: Int,
        val type: String
    )

    data class Attributes(
        val access_token: String,
        val created_at: Int,
        val expires_in: Int,
        val refresh_token: String,
        val token_type: String
    )
}