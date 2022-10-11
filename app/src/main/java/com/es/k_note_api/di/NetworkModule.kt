package com.es.k_note_api.di

import com.es.k_note_api.api.UserApi
import com.es.k_note_api.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit): UserApi {

        return retrofit.create(UserApi::class.java)
    }


}