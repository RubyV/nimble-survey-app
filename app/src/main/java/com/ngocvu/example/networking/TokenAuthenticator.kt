package com.ngocvu.example.networking


import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.utils.Prefs
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import javax.inject.Inject


class TokenAuthenticator @Inject constructor(
    private val prefs: Prefs,
    private val surveyRepo: Lazy<SurveyRepo>
) : Authenticator {
    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        return if (response?.code == 401) {
            CoroutineScope(Dispatchers.IO).launch {
                val refreshRes = surveyRepo.get().refreshToken(prefs.refreshToken!!)
                prefs.accessToken = refreshRes.data.attributes.accessToken
                prefs.refreshToken = refreshRes.data.attributes.refreshToken
            }
            response
                .request
                .newBuilder()
                .addHeader("Authorization", "Bearer " + prefs.accessToken).build()

        } else {
            return null
        }
    }


}
