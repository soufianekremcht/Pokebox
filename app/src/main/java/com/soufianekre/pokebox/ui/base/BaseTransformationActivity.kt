package com.soufianekre.pokebox.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import es.dmoral.toasty.Toasty


abstract class BaseTransformationActivity<T : ViewDataBinding,V:BaseViewModel> : TransformationAppCompatActivity() {


    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = mViewModel ?: getViewModel()
        mViewDataBinding?.executePendingBindings()
    }


    fun showInfo(msg: String) {
        Toasty.info(this, msg, Toasty.LENGTH_SHORT).show()

    }

    fun showSuccess(msg: String) {
        Toasty.info(this, msg, Toasty.LENGTH_SHORT).show()
    }

    fun showError(msg: String) {
        Toasty.error(this, msg, Toasty.LENGTH_SHORT).show()
    }
}

