package cz.procyon.tolkiendict.mobile

import android.app.Application
import android.content.Context
import cz.procyon.tolkiendict.mobile.di.daoModule
import cz.procyon.tolkiendict.mobile.di.databaseModule
import cz.procyon.tolkiendict.mobile.di.networkModule
import cz.procyon.tolkiendict.mobile.di.repositoryModule
import cz.procyon.tolkiendict.mobile.di.viewModelModule
import cz.procyon.tolkiendict.mobile.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.GlobalContext.startKoin

class TolkienDictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        // DI
        startKoin {
            androidContext(this@TolkienDictionaryApp)
            workManagerFactory()
            modules(
                listOf(
                    daoModule, databaseModule, repositoryModule,
                    viewModelModule, networkModule, workerModule
                )
            )
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
