package com.ngocvu.example.data.usecase

import com.ngocvu.example.data.entity.SurveyListEntity
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSurveyListUseCase @Inject constructor(
    private val userRepository: SurveyRepo,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun execute(): UseCaseResult<List<SurveyListEntity>> {
        return withContext(ioDispatcher) {
            try {
                val result = userRepository.getList()
                UseCaseResult.Success(result)
            } catch (exception: Exception) {
                UseCaseResult.Error(exception)
            }
        }
    }
}