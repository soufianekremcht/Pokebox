package com.soufianekre.pokebox.ui.pokemon_detail.base_stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soufianekre.pokebox.databinding.FragmentPokeDetailsBaseStatesBinding
import com.soufianekre.pokebox.ui.base.BaseFragment

class BaseStatsFragment : BaseFragment<FragmentPokeDetailsBaseStatesBinding, BaseStatsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): BaseStatsViewModel {
        TODO("Not yet implemented")
    }
}