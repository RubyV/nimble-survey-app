package com.ngocvu.example.data.repository

import com.ngocvu.example.BuildConfig
import com.ngocvu.example.data.request.AuthReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListResData
import com.ngocvu.example.networking.SurveyAppApi
import retrofit2.Response
import javax.inject.Inject

class SurveyRepo @Inject constructor(val api: SurveyAppApi){
    suspend fun getToken(email: String, password:String) : Response<AuthResData.Res>{
        var res =  api.auth(
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

    suspend fun getList() : Response<SurveyListResData.Res> {
        var res =  api.getSurvey(
            1,
            5
        )
        return res
    }

}