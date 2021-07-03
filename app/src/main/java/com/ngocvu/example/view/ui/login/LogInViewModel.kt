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
import retrofit2.Response
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: SurveyRepo,
) : ViewModel() {
    val loginRes = MutableLiveData<ViewState<Response<AuthResData.Res>>>()
    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun login(email: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loginRes.postValue(ViewState.Loading())
            val response = repository.getToken("dev@nimblehq.co", "12345678")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loginRes.postValue(ViewState.Success(response))
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}