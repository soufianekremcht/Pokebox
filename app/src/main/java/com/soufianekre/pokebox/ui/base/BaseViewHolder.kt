package com.soufianekre.pokebox.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(var itemView : View) : RecyclerView.ViewHolder(itemView){

    abstract fun onBind(position :Int)
}