package com.ngocvu.example.networking

import com.ngocvu.example.data.request.AuthReqData
import com.ngocvu.example.data.request.RefreshTokenReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListReqData
import retrofit2.http.*

interface SurveyAppApi {
    companion object {
        const val BASE_URL = "https://survey-api.nimblehq.co/api/v1/"
    }
    @POST("oauth/token")
    suspend fun auth(@Body authReqData: AuthReqData ): AuthResData.Res


    @GET("surveys?")
    suspend fun getSurvey(
        @Query("number") number: Int = 1,
        @Query("size")  size: Int = 5
     ): SurveyListReqData.Res


    @POST("oauth/token")
    fun refreshToken(@Body authReqData: RefreshTokenReqData): AuthResData.Res


}