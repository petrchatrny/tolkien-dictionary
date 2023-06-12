package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.BuildConfig
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.RetrofitConfig
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.DictionaryApi
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.TengwarApi
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit

val networkModule = module {
    // retrofit instances
    single(named("tencendil")) {
        RetrofitConfig(BuildConfig.TENCENDIL_API).createRetrofit()
    }

    single(named("dictionary")) {
        RetrofitConfig(BuildConfig.DICTIONARY_API).createRetrofit()
    }

    fun provideTengwarApi(): TengwarApi {
        return get<Retrofit>(
            clazz = Retrofit::class.java,
            qualifier = StringQualifier("tencendil")
        ).create(TengwarApi::class.java)
    }

    fun provideDictionaryApi(): DictionaryApi {
        return get<Retrofit>(
            clazz = Retrofit::class.java,
            qualifier = StringQualifier("dictionary")
        ).create(DictionaryApi::class.java)
    }

    single { provideTengwarApi() }
    single { provideDictionaryApi() }
}