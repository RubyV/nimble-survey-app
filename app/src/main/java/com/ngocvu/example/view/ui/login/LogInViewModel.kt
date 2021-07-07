package com.ngocvu.example.view.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListResData
import com.ngocvu.example.utils.Prefs
import com.ngocvu.example.utils.RegexUtil
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: SurveyRepo,
    private val prefs: Prefs
) : ViewModel() {
    val loginRes = MutableLiveData<ViewState<Response<AuthResData.Res>>>()
    var job: Job? = null


    fun validateEmail(email: String): Boolean {
        return RegexUtil.validateEmailAddress(email)
    }

    fun login(email: String, password: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            loginRes.postValue(ViewState.Loading())
            val response = repository.getToken("dev@nimblehq.co", "12345678")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    prefs.accessToken = response.body()!!.data.attributes.accessToken
                    prefs.refreshToken = response.body()!!.data.attributes.refreshToken
                    prefs.isLogged = true
                    loginRes.postValue(ViewState.Success(response))
                } else {
                    loginRes.postValue(ViewState.Error(response.message()))
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}