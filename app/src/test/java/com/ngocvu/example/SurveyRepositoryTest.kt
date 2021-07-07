package com.ngocvu.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.data.res.SurveyListResData
import com.ngocvu.example.networking.SurveyAppApi
import com.ngocvu.example.utils.TestCoroutineRule
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Error


class SurveyRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule  = TestCoroutineRule()

    lateinit var service: SurveyAppApi
    lateinit var repository: SurveyRepo
    lateinit var surveys: SurveyListResData.Res

    @Before
    fun setup() {
        service = mock(SurveyAppApi::class.java)
        repository = SurveyRepo(service)
        surveys = SurveyListResData.Res(
            listOf(
                SurveyListResData.Data(
                    SurveyListResData.Attributes("Title 1","Desc 1","CoverImage1"),
                    "Survey1","Survey"),
                SurveyListResData.Data(
                    SurveyListResData.Attributes("Title 2","Desc 2","CoverImage1"),
                    "Survey2","Survey"),
                SurveyListResData.Data(
                    SurveyListResData.Attributes("Title 3","Desc 3","CoverImage1"),
                    "Survey3","Survey")
            )
        )


    }

    @Test
    fun `Get Surveys - Should not null`() = runBlocking {
        whenever(repository.getList()).thenReturn(Response.success(surveys))
        assertNotNull(repository.getList())
        assertEquals(surveys,repository.getList().body())
    }

}