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
    private var rapAPIInstance: RapAPI? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                    .setLenient()
                    .create()
            }
            return gsonInstance
        }
    val getRapApi: RapAPI?
        get() {
            if (rapAPIInstance == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                rapAPIInstance = retrofit.create(RapAPI::class.java)
            }
            return rapAPIInstance
        }

    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance =
                context.getSharedPreferences("application_covid19", Context.MODE_PRIVATE)
        }
        return sharedPreferencesInstance
    }
}
