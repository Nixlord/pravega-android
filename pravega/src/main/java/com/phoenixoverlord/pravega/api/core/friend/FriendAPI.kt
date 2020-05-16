package com.phoenixoverlord.pravega.api.core.friend

import retrofit2.Call
import retrofit2.http.GET

public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): Call<HashMap<Int, Friend>>
}
