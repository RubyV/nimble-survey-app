package com.ngocvu.example.view.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListResData
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: SurveyRepo,
) : ViewModel() {
    val loginRes = MutableLiveData<ViewState<AuthResData.Res>>()
    var job: Job? = null
    fun login(email: String, password:String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            loginRes.postValue(ViewState.Loading())
            try {
                val response = repository.getToken("dev@nimblehq.co", "12345678")
                withContext(Dispatchers.Main) {
                    loginRes.postValue(ViewState.Success(response))
                }
            }
            catch (e: Exception)
            {
                loginRes.postValue(ViewState.Error(e.toString()))
            }
        }

    }



}