package com.ngocvu.example.view.ui.surveylist


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ngocvu.example.data.entity.SurveyListEntity
import com.ngocvu.example.data.usecase.GetSurveyListUseCase
import com.ngocvu.example.data.usecase.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class SurveyListViewModel @Inject constructor(
    private val getUsersUseCase: GetSurveyListUseCase
) : ViewModel() {
    // TODO: Implement the ViewModel
    var job: Job? = null
    private val _dataList = MutableLiveData<List<SurveyListEntity>>()
    val dataList: LiveData<List<SurveyListEntity>>
        get() = _dataList

    fun getAllSurvey() {
        job = CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                when (val result = getUsersUseCase.execute()) {
                    is UseCaseResult.Success -> _dataList.value = result.data
                }

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}