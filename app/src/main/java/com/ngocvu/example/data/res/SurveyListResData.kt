package com.ngocvu.example.data.res

import com.google.gson.annotations.SerializedName

sealed class SurveyListResData {

    data class Res(
        val data: List<Data>
    )

    data class Data(
        val attributes: Attributes,
        val id: String,
        val type: String
    )

    data class Attributes(
        val title: String,
        val description: String,
        @SerializedName("cover_image_url")
        val coverImageUrl: String,

        )
}