package com.soufianekre.pokebox.ui.pokemon_list

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.transformationlayout.TransformationLayout
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.databinding.ItemPokemonBinding
import com.soufianekre.pokebox.ui.pokemon_detail.PokemonDetailActivity
import com.soufianekre.pokebox.ui.pokemon_list.PokemonListAdapter.PokemonListViewHolder
import java.util.*


class PokemonListAdapter(var mContext: Context, var listener: PokemonAdapterListener?) :
    RecyclerView.Adapter<PokemonListViewHolder>() {

    companion object {
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
        var isLoaderVisible = false
    }

    private val items: MutableList<PokemonItem> = mutableListOf()
    var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val inflater = LayoutInflater.from(mContext)

        val binding = DataBindingUtil.inflate<ItemPokemonBinding>(
            inflater,
            R.layout.item_pokemon, parent, false
        )

        return PokemonListViewHolder(binding)


    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_NORMAL;
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.binding.apply {
            pokemon = items[position]
            executePendingBindings()
        }
        holder.onBind(position)
    }

    fun addAll(newPokemonList: ArrayList<PokemonItem>?) {
        items.clear()
        items.addAll(newPokemonList!!)
        notifyDataSetChanged()
    }

    fun insertAll(newPokemonList: ArrayList<PokemonItem>?) {
        val oldSize: Int = items.size
        items.addAll(newPokemonList!!)
        notifyItemRangeInserted(oldSize, items.size)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class PokemonListViewHolder(var binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val currentPokemon = items[position]
            binding.itemPokemonTransformationLayout.fadeMode = TransformationLayout.FadeMode.THROUGH
            binding.itemPokemonTransformationLayout.allContainerColors = Color.WHITE
            binding.itemPokemonTransformationLayout.containerColor = Color.WHITE
            //binding.itemPokemonTransformationLayout.scrimColor = Color.WHITE




            binding.root.setOnClickListener {
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.itemPokemonTransformationLayout.duration)
                    PokemonDetailActivity
                        .startActivityFromIntent(
                            mContext,
                            binding.itemPokemonTransformationLayout,
                            currentPokemon
                        )
                onClickedAt = currentClickedAt

            }
        }
    }
    interface PokemonAdapterListener {
        fun onPokemonClicked(pokemon: PokemonItem)
    }

}