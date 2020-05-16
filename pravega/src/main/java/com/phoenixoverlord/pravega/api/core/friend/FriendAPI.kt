package com.phoenixoverlord.pravega.api.core.friend

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET

fun<T> Single<T>.onResult(consumer: ((T?, Throwable?) -> Unit)): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(consumer)
}

public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): Single<Map<Int, Friend>>
}
