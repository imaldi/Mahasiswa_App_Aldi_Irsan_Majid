package com.aim2u.mahasiswa_app_aldi_irsan_majid.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl("http://192.168.43.6/mentoring_kotlin_week4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)
}