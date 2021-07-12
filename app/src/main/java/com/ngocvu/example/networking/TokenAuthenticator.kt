package com.ngocvu.example.networking

import com.google.gson.Gson
import com.ngocvu.example.BuildConfig
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.request.RefreshTokenReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.utils.Prefs
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.net.URL
import javax.inject.Inject


class TokenAuthenticator @Inject constructor(
    private val prefs: Prefs,
    private val repo: SurveyAppApi
    ): Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        return if (response?.code == 401) {
            val req = repo.refreshToken(
                RefreshTokenReqData("refresh_token", prefs.refreshToken, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
            ).data
            prefs.accessToken = req.attributes.accessToken
            prefs.refreshToken = req.attributes.refreshToken
            response
                .request
                .newBuilder()
                .addHeader("Authorization", "Bearer " + prefs.accessToken).build()
//            val client = OkHttpClient()
//            val url = URL("https://survey-api.nimblehq.co/api/v1/oauth/token")
//
//            val authRequest = RefreshTokenReqData("refresh_token", prefs.refreshToken, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
//            val requestJson = Gson().toJson(authRequest)
//
//            val mediaType = "application/json".toMediaTypeOrNull()
//            val body: RequestBody = RequestBody.create(mediaType, requestJson)
//
//            val request = Request.Builder()
//                .url(url)
//                .post(body)
//                .build()
//            val authResponse = client
//                .newCall(request)
//                .execute()
//            val auth = Gson().fromJson(authResponse.body?.string(), AuthResData.Res::class.java)
//            prefs.accessToken = auth.data.attributes.accessToken
//            prefs.refreshToken = auth.data.attributes.refreshToken
//            response
//                .request
//                .newBuilder()
//                .addHeader("Authorization", "Bearer " + prefs.accessToken).build()
        } else {
            return null
        }
    }




}
