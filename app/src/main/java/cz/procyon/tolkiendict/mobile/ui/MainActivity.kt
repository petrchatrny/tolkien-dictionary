package cz.procyon.tolkiendict.mobile.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.work.BackoffPolicy
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cz.procyon.tolkiendict.mobile.TolkienDictionaryApp
import cz.procyon.tolkiendict.mobile.navigation.graphs.RootNavigationGraph
import cz.procyon.tolkiendict.mobile.ui.theme.TolkienDictionaryTheme
import cz.procyon.tolkiendict.mobile.worker.DictionarySyncWorker
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        installSyncWorker(TolkienDictionaryApp.appContext)

        setContent {
            TolkienDictionaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph()
                }
            }
        }
    }

    private fun installSyncWorker(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<DictionarySyncWorker>(24, TimeUnit.HOURS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, Duration.ofHours(24))
            .build()

        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "dictionary_sync",
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                workRequest
            )
    }
}