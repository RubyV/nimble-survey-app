package com.ngocvu.example.networking

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.ngocvu.example.BuildConfig
import com.ngocvu.example.data.request.RefreshTokenReqData
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.utils.Prefs
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException
import java.net.URL


class TokenAuthenticator(private val prefs: Prefs): Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        return if (response?.code == 401) {

            val client = OkHttpClient()
            val url = URL("https://survey-api.nimblehq.co/api/v1/oauth/token")

            val authRequest = RefreshTokenReqData("refresh_token", prefs.accessToken, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET)
            val requestJson = Gson().toJson(authRequest)

            val mediaType = "application/json".toMediaTypeOrNull()
            val body: RequestBody = RequestBody.create(mediaType, requestJson)

            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            val authResponse = client.newCall(request).execute()
            val auth = Gson().fromJson(authResponse.body?.string(), AuthResData.Data::class.java)
            Log.d("Git", auth.toString())
            prefs.accessToken = auth.attributes.accessToken
            val httpUrl = response.request.url.newBuilder()
                .addQueryParameter("Authorization", "Bearer " + auth.attributes.accessToken)
                .build()
            response.request.newBuilder().url(httpUrl).build()
        } else {
            return null
        }
    }


}