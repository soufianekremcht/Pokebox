package com.soufianekre.pokebox.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.soufianekre.pokebox.MyViewModelFactory
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.databinding.ActivityMainBinding
import com.soufianekre.pokebox.ui.base.BaseActivity
import com.soufianekre.pokebox.ui.main.pokemon_list.PokemonListFragment
import kotlinx.android.synthetic.main.activity_pokemon_detail.view.*


class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {



    private var factory: MyViewModelFactory? = null
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var viewBinding: ActivityMainBinding

    private lateinit var drawerToggle :ActionBarDrawerToggle;



    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        viewBinding = getViewDataBinding()
        viewBinding.apply {
            vm = getCurrentViewModel()
        }
        setupUI()
        loadFragment(PokemonListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_main_refresh -> return false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI(){
        setSupportActionBar(viewBinding.mainToolbar)


        drawerToggle = ActionBarDrawerToggle(this,viewBinding.mainDrawerLayout,viewBinding.mainToolbar,R.string.open,R.string.close)
        drawerToggle.syncState()

        viewBinding.mainNavView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.drawer_pokemons -> {
                    showInfo("Pokemons")
                    loadFragment(PokemonListFragment())
                }
                R.id.drawer_contests -> showInfo("Contests")
                R.id.drawer_locations -> showInfo("Locations")
                R.id.drawer_evolution -> showInfo("Evolutions")
                R.id.drawer_settings -> showInfo("Settings")
                else -> showInfo("Drawer")
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getCurrentViewModel(): MainViewModel{
        mMainViewModel = ViewModelProvider(this,MyViewModelFactory()).get(MainViewModel::class.java)
        return mMainViewModel
    }
}