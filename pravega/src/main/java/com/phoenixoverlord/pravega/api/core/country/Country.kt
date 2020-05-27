package com.phoenixoverlord.pravega.api.core.country

import com.squareup.moshi.Json

data class Country (
    @Json(name ="name")     val name: String,
    @Json(name ="capital")  val capital: String,
    @Json(name ="region")   val region: String
)