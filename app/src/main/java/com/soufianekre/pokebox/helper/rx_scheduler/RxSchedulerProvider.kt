package com.soufianekre.pokebox.helper.rx_scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RxSchedulerProvider : RxScheduler{

    override fun IO(): Scheduler? {
        return Schedulers.io()
    }

    override fun computation(): Scheduler? {
        return Schedulers.computation()
    }

    override fun UI(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }
}