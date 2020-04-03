package com.phoenixoverlord.pravega.cloud.firebase


import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.phoenixoverlord.pravega.extensions.logDebug
import io.reactivex.Completable
import java.util.concurrent.CompletableFuture


val keys = listOf("predictor", "backend")

val defaultValues = mapOf(
"predictor" to "https://pravegapredictor.herokuapp.com",
"backend" to "https://pravegacore.herokuapp.com"
)

fun getFrom(remoteConfig: FirebaseRemoteConfig, keys: List<String>) = keys.associateWith { remoteConfig.getString(it) }

// Decrease API version after thinking. CompletableFuture requires Android >= 7
public fun remoteConfig(): CompletableFuture<Map<String, String>> {
    val future = CompletableFuture<Map<String, String>>()

    val remoteConfig = FirebaseRemoteConfig.getInstance()
    remoteConfig.setDefaultsAsync(defaultValues)
        .addOnSuccessListener {
            remoteConfig.fetch(2)
                .addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        remoteConfig.activate()
                        future.complete(getFrom(remoteConfig, keys))
                    }
                    else {
                        future.completeExceptionally(task.exception)
                    }
                }
        }

    return future
}