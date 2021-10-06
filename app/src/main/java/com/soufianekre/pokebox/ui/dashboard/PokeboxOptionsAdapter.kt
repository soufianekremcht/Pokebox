package com.soufianekre.pokebox.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.dashboard.PokeboxOption
import com.soufianekre.pokebox.databinding.ItemGridPokeboxOptionBinding
import com.soufianekre.pokebox.ui.base.BaseViewHolder

class PokeboxOptionsAdapter(var mContext : Context, var options : ArrayList<PokeboxOption>) :
    RecyclerView.Adapter<PokeboxOptionsAdapter.PokeboxOptionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeboxOptionViewHolder {
        val inflater = LayoutInflater.from(mContext)

        val binding = DataBindingUtil.inflate<ItemGridPokeboxOptionBinding>(
            inflater,
            R.layout.item_grid_pokebox_option, parent, false
        )

        return PokeboxOptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeboxOptionViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = options.size

    inner class PokeboxOptionViewHolder(var binding : ItemGridPokeboxOptionBinding) : BaseViewHolder(binding.root){
        override fun onBind(position: Int) {
            val item = options[position]
            binding.pokeboxOptionText.text = item.name
        }

    }
}