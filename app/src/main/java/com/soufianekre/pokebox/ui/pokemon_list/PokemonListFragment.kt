package com.soufianekre.pokebox.ui.pokemon_list

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.soufianekre.pokebox.MyViewModelFactory
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.databinding.FragmentPokemonListBinding
import com.soufianekre.pokebox.ui.base.BaseFragment
import com.soufianekre.pokebox.ui.views.SpaceItemDecoration
import com.soufianekre.pokebox.ui.views.PaginationListener

class PokemonListFragment : BaseFragment<FragmentPokemonListBinding,PokemonListViewModel>() {

    private var isLoading : Boolean = false
    private var currentPage : Int = 0
    private var isLastPage : Boolean = false
    private var PAGE_SIZE : Int = 20

    companion object{
        const val POKEMON_TO_SHOW = "pokemon_to_show"
    }

    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var mPokemonListViewModel : PokemonListViewModel
    lateinit var viewBinding : FragmentPokemonListBinding

    lateinit var pokemonAdapter : PokemonListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding()
        viewBinding.pokemonRecyclerView.setHasFixedSize(true)
        gridLayoutManager = GridLayoutManager(activity,2,VERTICAL,false)
        viewBinding.pokemonRecyclerView.layoutManager = gridLayoutManager

        viewBinding.pokemonRecyclerView.addItemDecoration(SpaceItemDecoration())

        pokemonAdapter =  PokemonListAdapter(requireContext(), null)
        viewBinding.pokemonRecyclerView.adapter =pokemonAdapter


        getPokemonsData()

        /**
         * add scroll listener while user reach in bottom load more will call
         */

        viewBinding.pokemonRecyclerView.addOnScrollListener(object :PaginationListener(gridLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                viewBinding.pokemonProgressBar.visibility = View.VISIBLE
                getViewModel().fetchPokemons( PAGE_SIZE,currentPage)
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        });

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_main_refresh ->{
                showInfo("The list is refreshing now.")
                currentPage = 0;
                isLastPage = false;
                pokemonAdapter.clear()
                getViewModel().fetchPokemons(currentPage,PAGE_SIZE)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pokemon_list
    }

    override fun getViewModel(): PokemonListViewModel {
        mPokemonListViewModel = ViewModelProvider(this,MyViewModelFactory())
            .get(PokemonListViewModel::class.java)
        return mPokemonListViewModel
    }

    fun getPokemonsData(){
        getViewModel().pokemonListInfo.observe(viewLifecycleOwner, Observer {
            if (pokemonAdapter.itemCount == 0)
                pokemonAdapter.addAll(it as ArrayList<PokemonItem>)
            else
                pokemonAdapter.insertAll(it as java.util.ArrayList<PokemonItem>)

            isLoading =false
            viewBinding.pokemonProgressBar.visibility = View.GONE

        })
    }

}