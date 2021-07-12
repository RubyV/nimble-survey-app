package com.ngocvu.example.di

import android.content.Context
import com.ngocvu.example.data.repository.SurveyRepo
import com.ngocvu.example.networking.SurveyAppApi
import com.ngocvu.example.networking.TokenAuthenticator
import com.ngocvu.example.utils.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @OkHttpClientQualifier
    @JvmStatic

    internal fun provideOkHttpClient(prefs: Prefs, @SurveyApiStandard api: SurveyAppApi): OkHttpClient {
        var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        var builder = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .authenticator(TokenAuthenticator(prefs, api))
            .build()
        return builder
    }

    @Provides
    @SurveyApiStandard
    @Singleton
    fun provideRetrofit(@OkHttpClientQualifier okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SurveyAppApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideSurveyApi(@SurveyRetrofitInterfaceStandard retrofit: Retrofit): SurveyAppApi =
        retrofit.create(SurveyAppApi::class.java)


    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs = Prefs(context)

    @Provides
    @Singleton
    fun provideRepo(@SurveyApiStandard api: SurveyAppApi): SurveyRepo = SurveyRepo(api)

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

}

annotation class IoDispatcher

annotation class SurveyRetrofitInterfaceStandard

annotation class SurveyApiStandard

annotation class OkHttpClientQualifier
