package com.pba.di

import com.pba.core.di.moduleCore
import org.koin.dsl.KoinConfiguration

fun createKoinConfig(): KoinConfiguration {
    return KoinConfiguration {
        modules(
            moduleCore,
            modulesApp,
            modulePlatform,
            moduleDb,
        )
    }
}