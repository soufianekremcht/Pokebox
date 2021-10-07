package com.soufianekre.pokebox.ui.pokemon_detail.base_stats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonTypeInfo
import com.soufianekre.pokebox.ui.base.BaseViewHolder

class PokemonStrongPointsAdapter(var mContext: Context, var typeInfos: ArrayList<PokemonTypeInfo>) :
    RecyclerView.Adapter<PokemonStrongPointsAdapter.PokemonStrongPointsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonStrongPointsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon_attributes, parent, false)

        return PokemonStrongPointsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonStrongPointsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return typeInfos.size
    }


    inner class PokemonStrongPointsViewHolder(var v: View) : BaseViewHolder(v) {


        override fun onBind(position: Int) {

            val typeText = v.findViewById<TextView>(R.id.pokemon_attribute_text)


            typeText.text = typeInfos[position].name
        }

    }

}