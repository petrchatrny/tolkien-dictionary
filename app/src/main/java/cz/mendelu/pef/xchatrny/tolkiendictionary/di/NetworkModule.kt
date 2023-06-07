package cz.mendelu.pef.xchatrny.tolkiendictionary.di

import cz.mendelu.pef.xchatrny.tolkiendictionary.api.RetrofitConfig
import cz.mendelu.pef.xchatrny.tolkiendictionary.api.endpoints.TengwarApi
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit

// TODO hardcoded URL
val networkModule = module {
    // retrofit instances
    single(named("tencendil")) {
        RetrofitConfig("https://www.tecendil.com").createRetrofit()
    }

    fun provideTengwarApi(): TengwarApi {
        return get<Retrofit>(
            clazz = Retrofit::class.java,
            qualifier = StringQualifier("tencendil")
        ).create(TengwarApi::class.java)
    }

    single { provideTengwarApi() }
}