package cz.mendelu.pef.xchatrny.tolkiendictionary

import android.app.Application
import android.content.Context
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.daoModule
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.databaseModule
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.networkModule
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.repositoryModule
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.viewModelModule
import cz.mendelu.pef.xchatrny.tolkiendictionary.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin


class TolkienDictionaryApp : Application(), KoinComponent {

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
