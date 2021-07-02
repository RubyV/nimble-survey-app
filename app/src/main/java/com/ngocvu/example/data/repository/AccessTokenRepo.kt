package com.ngocvu.example.data.repository

import android.util.Log
import com.ngocvu.example.BuildConfig
import com.ngocvu.example.data.request.AuthReqData
import com.ngocvu.example.data.request.RefreshTokenReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListReqData
import com.ngocvu.example.networking.SurveyAppApi
import com.ngocvu.example.utils.Prefs
import javax.inject.Inject

class AccessTokenRepo @Inject constructor(val api: SurveyAppApi){
    suspend fun getToken(email: String, password:String) : AuthResData.Res{
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

    suspend fun getList() : SurveyListReqData.Res{
        var res =  api.getSurvey(
            1,
            3
        )
        return res

    }

//    fun refreshToken() : AuthResData.Res{
//        var res =  api.refreshToken(
//            RefreshTokenReqData(
//                "refresh_token",
//                "WN9LHOUTgJbALXFx2oj64FPrfC1-kQXpgo2fqC1rwKo",
//                BuildConfig.CLIENT_ID,
//                BuildConfig.CLIENT_SECRET,
//            )
//        )
//        return res
//
//    }
}