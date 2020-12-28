package com.esiea.android4A.injection

import org.koin.dsl.module
import com.esiea.android4A.presentation.login.LoginViewModel
import android.content.Context
import androidx.room.Room
import com.esiea.android4A.data.local.AppDatabase
import com.esiea.android4A.data.local.DatabaseDao
import com.esiea.android4A.data.repository.UserRepository
import com.esiea.android4A.domain.usecase.CreateUserUseCase
import com.esiea.android4A.domain.usecase.GetUserUseCase
import com.esiea.android4A.presentation.countryinfo.InfoViewModel
import com.esiea.android4A.presentation.countrylist.ListViewModel
import com.esiea.android4A.presentation.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext

val presentationModule = module {
    factory { LoginViewModel(get()) }
    factory { RegisterViewModel(get())}
    factory { ListViewModel() }
    factory { InfoViewModel() }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get())}
}

val dataModule = module {
    single { UserRepository(get()) }
    single { createDatabase(androidContext())}
}

fun createDatabase(context: Context): DatabaseDao{
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}