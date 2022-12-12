package com.es.k_note_api.di

import com.es.k_note_api.api.AuthInterceptor
import com.es.k_note_api.api.NoteApi
import com.es.k_note_api.api.UserApi
import com.es.k_note_api.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.baseUrl)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit.Builder): UserApi {
        return retrofit.build().create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNoteApi(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): NoteApi {


        return retrofit.client(okHttpClient).build().create(NoteApi::class.java)
    }
}