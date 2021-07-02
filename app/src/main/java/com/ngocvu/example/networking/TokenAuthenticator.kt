package com.ngocvu.example.networking

import android.content.Context
import android.util.Log
import com.google.gson.Gson
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

            val authRequest = RefreshTokenReqData("refresh_token", prefs.accessToken, "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw", "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw")
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
            prefs.accessToken = auth.attributes.access_token
            val httpUrl = response.request.url.newBuilder()
                .addQueryParameter("Authorization", "Bearer " + auth.attributes.access_token)
                .build()
            response.request.newBuilder().url(httpUrl).build()
        } else {
            return null
        }
    }


}