package com.soufianekre.pokebox.repository

import com.soufianekre.pokebox.helper.rx_scheduler.RxScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

object TestRxSchedulersProvider : RxScheduler {

    override fun IO(): Scheduler? {
        return Schedulers.trampoline()
    }

    override fun computation(): Scheduler? {
        return Schedulers.trampoline()
    }

    override fun UI(): Scheduler? {
        return Schedulers.trampoline()
    }
}