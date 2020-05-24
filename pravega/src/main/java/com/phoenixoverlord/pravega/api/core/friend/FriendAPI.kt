package com.phoenixoverlord.pravega.api.core.friend

import com.phoenixoverlord.pravega.extensions.logDebug
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import java.lang.Error

fun<T> Single<T>.onResult(consumer: ((T, Throwable?) -> Unit)): Disposable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { data, error  ->
            val err = if (data == null) Error("NULL DATA") else error
            consumer(data, err)
        }
}

public interface FriendAPI {
    @GET("/friends")
    fun getAllFriends(): Single<Map<Int, Friend>>
}
