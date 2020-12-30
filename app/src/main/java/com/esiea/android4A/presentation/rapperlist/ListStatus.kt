package com.esiea.android4A.presentation.rapperlist

import com.esiea.android4A.domain.entity.Rapper

sealed class ListStatus

data class ListSuccess(val rappers : List<Rapper>) : ListStatus()
object ListError : ListStatus()