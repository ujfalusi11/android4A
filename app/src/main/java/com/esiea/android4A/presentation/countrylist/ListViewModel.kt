package com.esiea.android4A.presentation.countrylist

import androidx.lifecycle.MutableLiveData
import com.esiea.android4A.Singletons
import com.esiea.android4A.data.repository.RestRapResponse
import com.esiea.android4A.domain.entity.Rapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel {
    val status : MutableLiveData<ListStatus> = MutableLiveData()
    val rapperLiveData: MutableLiveData<Rapper> = MutableLiveData()
    var rapperList: List<Rapper>? = null

    init{
        apiCall()
    }

    private fun apiCall() {
        val call = Singletons.getRapApi?.getRapResponse
        call?.enqueue(object: Callback<RestRapResponse> {
            override fun onResponse(call: Call<RestRapResponse>, response: Response<RestRapResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val rappers = response.body()!!.getRappers
                    status.value = if(rappers != null){
                        rapperList = rappers
                        ListSuccess(rappers)
                    }else{
                        ListError
                    }
                }
                else{
                    status.value = ListError
                }
            }
            override fun onFailure(call: Call<RestRapResponse>, t:Throwable) {
                status.value = ListError
            }
        })
    }

    fun onItemClick(position: Int) {
        rapperLiveData.value = rapperList!![position]
    }
}