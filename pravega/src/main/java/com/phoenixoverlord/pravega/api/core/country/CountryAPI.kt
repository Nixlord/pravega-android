package com.phoenixoverlord.pravega.api.core.country

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountryAPI {
    @Headers(
        "x-rapidapi-host: restcountries-v1.p.rapidapi.com",
        "x-rapidapi-key: 51a71f54a2mshee721f54be228abp16c1c0jsnd3ad68a8fd86",
        "useQueryString: true"
    )
    @GET("/all")
    fun getAllCountries(): Single<List<Country>>

/*
* https://restcountries-v1.p.rapidapi.com/all
*
* headers -
*   x-rapidapi-host - restcountries-v1.p.rapidapi.com
*   x-rapidapi-key - 51a71f54a2mshee721f54be228abp16c1c0jsnd3ad68a8fd86
*   useQueryString - true
*/

}