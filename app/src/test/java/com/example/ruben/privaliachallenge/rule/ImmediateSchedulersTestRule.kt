package com.example.ruben.privaliachallenge.rule

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import rx.Scheduler
import rx.android.plugins.RxAndroidPlugins
import rx.android.plugins.RxAndroidSchedulersHook
import rx.plugins.RxJavaHooks
import rx.schedulers.Schedulers

class ImmediateSchedulersTestRule: TestRule {

    private val ImmediateRxAndroidSchedulersHook = object : RxAndroidSchedulersHook() {
        override fun getMainThreadScheduler(): Scheduler {
            return Schedulers.immediate()
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.getInstance().reset()
                RxAndroidPlugins.getInstance().registerSchedulersHook(ImmediateRxAndroidSchedulersHook)

                RxJavaHooks.setOnIOScheduler { Schedulers.immediate() }
                RxJavaHooks.setOnComputationScheduler { Schedulers.immediate() }
                RxJavaHooks.setOnNewThreadScheduler { Schedulers.immediate() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaHooks.reset()
                    RxAndroidPlugins.getInstance().reset()
                }
            }
        }
    }
}