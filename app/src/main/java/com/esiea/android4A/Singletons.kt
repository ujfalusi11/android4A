package com.esiea.android4A

import android.content.Context
import android.content.SharedPreferences
import com.esiea.android4A.data.remote.RapAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Singletons {
    private var gsonInstance: Gson? = null

    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }
}
