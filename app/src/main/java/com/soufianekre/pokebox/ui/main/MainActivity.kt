package com.soufianekre.pokebox.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skydoves.transformationlayout.onTransformationStartContainer
import com.soufianekre.pokebox.MyViewModelFactory
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.databinding.ActivityMainBinding
import com.soufianekre.pokebox.ui.base.BaseActivity
import com.soufianekre.pokebox.ui.pokemon_list.PokemonListFragment


class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {



    private var factory: MyViewModelFactory? = null
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var viewBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        viewBinding = getViewDataBinding()
        viewBinding.apply {
            vm = getViewModel()
        }
        setupUI()
        loadFragment(PokemonListFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupUI(){
        setSupportActionBar(viewBinding.mainToolbar)
    }

    private fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel{
        mMainViewModel = ViewModelProvider(this,MyViewModelFactory()).get(MainViewModel::class.java)
        return mMainViewModel
    }
}