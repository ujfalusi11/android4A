package com.esiea.android4A.presentation.countrylist

import com.esiea.android4A.domain.entity.Rapper

sealed class ListStatus

data class ListSuccess(val rappers : List<Rapper>) : ListStatus()
object ListError : ListStatus()