package com.ngocvu.example.view.ui.surveylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.res.SurveyListResData
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class SurveyListViewModel @Inject constructor(
    private val repository: SurveyRepo,
): ViewModel() {
    // TODO: Implement the ViewModel
    val dataList = MutableLiveData<ViewState<Response<SurveyListResData.Res>>>()
    var job: Job? = null

    fun getAllSurvey() {
        job = CoroutineScope(Dispatchers.IO).launch {
            dataList.postValue(ViewState.Loading())

            val response = repository.getList()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful)
                {
                    dataList.postValue(ViewState.Success(response))
                }
                else
                {
                    dataList.postValue(ViewState.Error(response.message()))
                }

                }
        }

    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}