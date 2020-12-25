package com.esiea.android4A.presentation.login


sealed class LoginStatus

object LoginSuccess : LoginStatus()
object LoginNotFound : LoginStatus()


