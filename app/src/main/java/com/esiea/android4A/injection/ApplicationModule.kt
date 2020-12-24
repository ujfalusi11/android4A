package com.esiea.android4A.injection

import org.koin.dsl.module
import com.esiea.android4A.presentation.main.MainViewModel
import android.content.Context
import androidx.room.Room
import com.esiea.android4A.data.local.AppDatabase
import com.esiea.android4A.data.local.DatabaseDao
import com.esiea.android4A.data.repository.UserRepository
import com.esiea.android4A.domain.usecase.CreateUserUseCase
import com.esiea.android4A.domain.usecase.GetUserUseCase
import org.koin.android.ext.koin.androidContext
import java.security.AccessControlContext

val presentationModule = module {
    factory { MainViewModel(get(), get()) }
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