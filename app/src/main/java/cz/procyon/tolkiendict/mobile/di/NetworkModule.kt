package cz.procyon.tolkiendict.mobile.di

import cz.procyon.tolkiendict.mobile.BuildConfig
import cz.procyon.tolkiendict.mobile.api.RetrofitConfig
import cz.procyon.tolkiendict.mobile.api.endpoint.DictionaryApi
import cz.procyon.tolkiendict.mobile.api.endpoint.TengwarApi
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