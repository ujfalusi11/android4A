package com.esiea.android4A.presentation.register

sealed class RegisterStatus

object RegisterSuccess : RegisterStatus()
object RegisterError : RegisterStatus()