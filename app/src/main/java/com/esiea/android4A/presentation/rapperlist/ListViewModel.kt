package com.esiea.android4A.presentation.rapperlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esiea.android4A.Constants
import com.esiea.android4A.data.remote.RapAPI
import com.esiea.android4A.data.repository.RestRapResponse
import com.esiea.android4A.domain.entity.Rapper
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel (
): ViewModel() {
    val apiLiveData: MutableLiveData<ListStatus> = MutableLiveData()
    val rapperLiveData: MutableLiveData<Rapper> = MutableLiveData()
    var rapperList: List<Rapper>? = null

    fun apiCall(){
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val rapApi = retrofit.create(RapAPI::class.java)
        rapApi.rappers.enqueue(object : Callback<RestRapResponse?> {
            override fun onResponse(
                call: Call<RestRapResponse?>?,
                response: Response<RestRapResponse?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val rapList = response.body()!!.rappers
                    rapperList=rapList
                    apiLiveData.value = ListSuccess(rapList)
                }
            }

            override fun onFailure(call: Call<RestRapResponse?>?, t: Throwable?) {
                apiLiveData.value = ListError
            }
        })
    }

    fun onItemClick(position: Int) {
        rapperLiveData.value = rapperList!![position]
    }
}