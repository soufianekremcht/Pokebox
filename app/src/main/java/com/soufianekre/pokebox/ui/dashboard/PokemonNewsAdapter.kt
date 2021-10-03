package com.soufianekre.pokebox.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.dashboard.PokemonNews
import com.soufianekre.pokebox.databinding.ItemListPokemonNewsBinding
import com.soufianekre.pokebox.databinding.ItemPokemonBinding
import com.soufianekre.pokebox.ui.base.BaseViewHolder

class PokemonNewsAdapter(var mContext: Context, var pokemonNewsList: ArrayList<PokemonNews>) :
    RecyclerView.Adapter<PokemonNewsAdapter.PokemonNewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonNewsViewHolder {

        val inflater = LayoutInflater.from(mContext)

        val binding = DataBindingUtil.inflate<ItemListPokemonNewsBinding>(
            inflater,
            R.layout.item_list_pokemon_news, parent, false
        )


        return PokemonNewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonNewsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = pokemonNewsList.size


    inner class PokemonNewsViewHolder(var binding: ItemListPokemonNewsBinding) :
        BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {
            val item = pokemonNewsList[position]

            binding.pokeNewsTitle.text = item.title
            binding.pokeNewsDate.text = item.date
            Glide.with(binding.root)
                .asBitmap()
                .load(R.drawable.img_thumbnail)
                .into(binding.pokeNewsImage)
        }

    }
}