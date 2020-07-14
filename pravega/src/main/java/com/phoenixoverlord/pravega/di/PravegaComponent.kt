package com.phoenixoverlord.pravega.di

import com.phoenixoverlord.pravega.api.PravegaService
import com.phoenixoverlord.pravega.framework.PravegaActivity
import dagger.Component

@Component(modules=[PravegaService::class])
interface PravegaComponent {

    //This tells dagger that MainActivity requests injection so the graph needs to
    //satisfy all dependencies of the fields MainActivity is requesting.
    fun inject(activity: PravegaActivity)
}