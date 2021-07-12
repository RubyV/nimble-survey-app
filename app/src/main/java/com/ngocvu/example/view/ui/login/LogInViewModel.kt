package com.ngocvu.example.view.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.utils.Prefs
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: SurveyRepo,
    private val prefs: Prefs
) : ViewModel() {
    val loginRes = MutableLiveData<ViewState<AuthResData.Res>>()
    var job: Job? = null

    fun login(email: String, password: String) {

        job = CoroutineScope(Dispatchers.IO).launch {
            loginRes.postValue(ViewState.Loading())
            try {
                val response = repository.getToken(email, password)
                prefs.accessToken = response.data.attributes.accessToken
                prefs.refreshToken = response.data.attributes.refreshToken
                prefs.isLogged = true
                loginRes.postValue(ViewState.Success(response))
            } catch (e: Exception) {
                loginRes.postValue(ViewState.Error(e.message))
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}