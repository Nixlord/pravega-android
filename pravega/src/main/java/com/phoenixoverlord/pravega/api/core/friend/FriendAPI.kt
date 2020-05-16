package com.phoenixoverlord.pravega.api.core.friend

import io.reactivex.Observable
import io.reactivex.Single

import retrofit2.http.GET

public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): Single<Map<Int, Friend>>
}
