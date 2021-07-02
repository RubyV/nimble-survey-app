package com.ngocvu.example.view.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.AccessTokenRepo
import com.ngocvu.example.data.res.AuthResData
import com.ngocvu.example.data.res.SurveyListReqData
import com.ngocvu.example.utils.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class LogInViewModel @Inject constructor(
    private val repository: AccessTokenRepo,
) : ViewModel() {
    val movieList = MutableLiveData<AuthResData.Res>()
    val dataList = MutableLiveData<SurveyListReqData.Res>()

    var job: Job? = null
    fun login(email: String, password:String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getToken("dev@nimblehq.co", "12345678")
            withContext(Dispatchers.Main) {
                try {
                    Log.d("Git", response.toString())
                    movieList.postValue(response)
                }
                catch (e: Exception)
                {
                    Log.d("Git", e.toString())
                }

            }
        }

    }



}