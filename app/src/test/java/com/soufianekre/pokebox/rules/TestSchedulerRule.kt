package com.soufianekre.pokebox.rules

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


public class TestSchedulerRule : TestRule {
    private val testScheduler: TestScheduler = TestScheduler()
    fun getTestScheduler(): TestScheduler {
        return testScheduler
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { testScheduler }
                RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                }
            }
        }
    }
}