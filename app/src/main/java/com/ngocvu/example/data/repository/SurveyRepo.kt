package com.ngocvu.example.data.repository

import com.ngocvu.example.BuildConfig
import com.ngocvu.example.data.entity.SurveyListEntity
import com.ngocvu.example.data.entity.toSurveyListEntity
import com.ngocvu.example.data.request.AuthReqData
import com.ngocvu.example.data.request.RefreshTokenReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.networking.SurveyAppApi
import javax.inject.Inject

class SurveyRepo @Inject constructor(
    val api: SurveyAppApi
) {
    suspend fun getToken(email: String, password: String): AuthResData.Res {
        val res = api.auth(
            AuthReqData(
                "password",
                email,
                password,
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
            )
        )
        return res
    }

    suspend fun getList(): List<SurveyListEntity> {
        val res = api.getSurvey(
            1,
            5
        )
        return res.data.toSurveyListEntity()
    }

        fun refreshToken(refreshToken: String): AuthResData.Res {
        val res = api.refreshToken(
            RefreshTokenReqData(
                "refresh_token",
                refreshToken,
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
            )
        )
        return res
    }

}