package com.example.ruben.privaliachallenge

import android.app.Application
import android.content.Context
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
        application = AndroidTest.TMDbApplication::class,
        sdk = intArrayOf(21))
abstract class AndroidTest {

    fun context(): Context {
        return RuntimeEnvironment.application
    }

    internal class TMDbApplication : Application()

}