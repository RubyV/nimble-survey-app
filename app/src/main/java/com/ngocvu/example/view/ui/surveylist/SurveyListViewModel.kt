package com.ngocvu.example.view.ui.surveylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.repository.AccessTokenRepo
import com.ngocvu.example.data.res.SurveyListReqData
import com.ngocvu.example.view.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class SurveyListViewModel @Inject constructor(
    private val repository: AccessTokenRepo,
): ViewModel() {
    // TODO: Implement the ViewModel
    val dataList = MutableLiveData<ViewState<SurveyListReqData.Res>>()
    var job: Job? = null
    fun getAllSurvey() {
        job = CoroutineScope(Dispatchers.IO).launch {
            dataList.postValue(ViewState.Loading())
            try {
                val response = repository.getList()
                withContext(Dispatchers.Main) {

                    dataList.postValue(ViewState.Success(response))
                }
            }
            catch (e: Exception)
            {
                dataList.postValue(ViewState.Error("Error fetching survey"))
            }

        }

    }
}