package com.phoenixoverlord.pravega.api

import com.phoenixoverlord.pravega.Server
import com.phoenixoverlord.pravega.api.core.country.CountryAPI
import com.phoenixoverlord.pravega.api.core.friend.FriendAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Needs Proper design
 */
@Module
class PravegaService(server: Server) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val AI: Retrofit
    val API: Retrofit

    val friendAPI: FriendAPI
    val countryAPI: CountryAPI

    @Provides
    fun createAdapter(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun countryApi(retrofit: Retrofit): CountryAPI {
        return retrofit.create(CountryAPI::class.java)
    }

    @Provides
    fun friendsApi(retrofit: Retrofit): FriendAPI {
        return retrofit.create(FriendAPI::class.java)
    }

    init {
        AI = createAdapter(server.AI)
        API = createAdapter(server.API)

        friendAPI = API.create(FriendAPI::class.java)

        /*Just added for testing Dagger2*/
        countryAPI = createAdapter("https://restcountries-v1.p.rapidapi.com")
            .create(CountryAPI::class.java)
    }
}
