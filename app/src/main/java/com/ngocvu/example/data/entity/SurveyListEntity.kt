package com.ngocvu.example.data.entity

import com.ngocvu.example.data.res.SurveyListResData

    data class SurveyListEntity(
    val id: String,
    val title: String,
    val description: String,
    val coverImageUrl: String )


fun List<SurveyListResData.Data>.toSurveyListEntity(): List<SurveyListEntity> {
    return this.map { it.toEntity() }
}

private fun SurveyListResData.Data.toEntity(): SurveyListEntity {
    return SurveyListEntity(
        id = this.id,
        title = this.attributes.title,
        description = this.attributes.description,
        coverImageUrl = this.attributes.coverImageUrl,

    )
}
