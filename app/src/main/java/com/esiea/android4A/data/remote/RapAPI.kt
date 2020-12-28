package com.esiea.android4A.data.remote

import com.esiea.android4A.data.repository.RestRapResponse
import retrofit2.Call
import retrofit2.http.GET

interface RapAPI {
    @get:GET("/summary")
    val getRapResponse: Call<RestRapResponse>
}