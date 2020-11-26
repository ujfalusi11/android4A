package com.esiea.android4A.injection

import org.koin.dsl.module
import com.esiea.android4A.MainViewModel

val presentationModule = module {
    factory { MainViewModel() }
}