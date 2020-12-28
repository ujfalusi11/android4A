package com.esiea.android4A.presentation.countryinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.esiea.android4A.domain.entity.Rapper

class InfoViewModel : ViewModel(){
    val realName :  MutableLiveData<String> = MutableLiveData()
    val rapperName : MutableLiveData<String> = MutableLiveData()
    val topAlbum: MutableLiveData<String> = MutableLiveData()
    val bio: MutableLiveData<String> = MutableLiveData()
    val img: MutableLiveData<String> = MutableLiveData()

    fun info(rapper: Rapper){
        realName.value = rapper.realName
        rapperName.value = rapper.rapperName
        topAlbum.value = rapper.topAlbum
        bio.value = rapper.bio
        img.value = rapper.url
        /*
        if(country.description == ""){
            descriptionText.value = "pas de description disponible pour cette galaxie"
        }else{
            descriptionText.value = country.description
        }*/
        //image.value = galaxie.url
    }
}