package cz.procyon.tolkiendict.mobile.ui.screen.software_lib

import cz.procyon.tolkiendict.mobile.architecture.BaseViewModel

class SoftwareLibsViewModel : BaseViewModel() {
    val libraries = listOf(
        SoftwareLibrary(
            "Compose Material3 Components",
            "androidx.compose.material3:material3",
            Licence.APACHE
        ),
        SoftwareLibrary(
            "Compose Material Icons Extended",
            "androidx.compose.material:material-icons-extended",
            Licence.APACHE
        ),
        SoftwareLibrary("SplashScreen", "androidx.core:core-splashscreen", Licence.APACHE),
        SoftwareLibrary("Koin Core", "io.insert-koin:koin-core", Licence.APACHE),
        SoftwareLibrary("Koin Android", "io.insert-koin:koin-android", Licence.APACHE),
        SoftwareLibrary(
            "Koin AndroidX Compose",
            "io.insert-koin:koin-androidx-compose",
            Licence.APACHE
        ),
        SoftwareLibrary("swipe", "me.saket.swipe:swipe", Licence.APACHE),

        SoftwareLibrary("Retrofit", "com.squareup.retrofit2:retrofit", Licence.APACHE),
        SoftwareLibrary(
            "Converter: Moshi",
            "com.squareup.retrofit2:converter-moshi",
            Licence.APACHE
        ),
        SoftwareLibrary("Moshi Kotlin", "com.squareup.moshi:moshi-kotlin", Licence.APACHE),
        SoftwareLibrary("Jsoup Java HTML Parser", "org.jsoup:jsoup", Licence.MIT),
        SoftwareLibrary("ComposePrefs3", "com.github.JamalMulla:ComposePrefs3", Licence.MIT)
    ).sortedBy { it.name }


    data class SoftwareLibrary(
        val name: String,
        val packageName: String,
        val licence: Licence
    )

    enum class Licence(val displayName: String) {
        MIT("MIT"),
        APACHE("Apache 2.0")
    }
}