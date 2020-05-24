package com.phoenixoverlord.pravega.api.core.friend

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import java.lang.Error

// Its still bad, simplify.
fun<T> Single<T>.onResult(consumer: ((T, Throwable?) -> Unit)): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { data, error  ->
            val err = data?.run { error } ?: Error("NULL Data")
            consumer(data, err)
        }
}

public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): Single<Map<Int, Friend>>
}
