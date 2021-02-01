package com.soufianekre.pokebox.helper.rx_scheduler

import io.reactivex.Scheduler

interface RxScheduler {

    fun IO(): Scheduler?
    fun computation(): Scheduler?
    fun UI(): Scheduler?
}