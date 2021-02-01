package com.soufianekre.pokebox.helper

import androidx.annotation.NonNull
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RxHelper {
    fun <T> getObservable(@NonNull observable: Observable<T>): Observable<T> {
        return observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> safeObservable(@NonNull observable: Observable<T>): Observable<T> {
        return getObservable(observable)
            .doOnError(Throwable::printStackTrace)
    }

    fun <T> getSingle(@NonNull single: Single<T>): Single<T> {
        return single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getFlowable(@NonNull flowable: Flowable<T>): Flowable<T> {
        return flowable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}