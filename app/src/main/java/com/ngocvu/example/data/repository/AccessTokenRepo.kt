package com.ngocvu.example.data.repository

import android.util.Log
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
                "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
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

    fun refreshToken() : AuthResData.Res{
        var res =  api.refreshToken(
            RefreshTokenReqData(
                "refresh_token",
                "WN9LHOUTgJbALXFx2oj64FPrfC1-kQXpgo2fqC1rwKo",
                "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
            )
        )
        return res

    }
}