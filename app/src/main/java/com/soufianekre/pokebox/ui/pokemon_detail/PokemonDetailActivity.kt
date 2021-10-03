package com.soufianekre.pokebox.ui.pokemon_detail

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.soufianekre.pokebox.MyViewModelFactory
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.databinding.ActivityPokemonDetailBinding
import com.soufianekre.pokebox.ui.base.BaseTransformationActivity
import com.soufianekre.pokebox.ui.pokemon_list.PokemonListFragment.Companion.POKEMON_TO_SHOW
import timber.log.Timber


class PokemonDetailActivity :
    BaseTransformationActivity<ActivityPokemonDetailBinding, PokemonDetailViewModel>() {

    private lateinit var mPokemonDetailViewModel: PokemonDetailViewModel
    private lateinit var binding: ActivityPokemonDetailBinding

    private var currentPokemon: PokemonItem? = PokemonItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntents()
        binding = getViewDataBinding()
        binding.apply {
            lifecycleOwner = this@PokemonDetailActivity
        }

        getViewModel().fetchPokemonInfo(currentPokemon?.name!!)
        setupUI()


    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pokemon_detail, menu)
        var menuIdItem: MenuItem = menu!!.findItem(R.id.menu_pokemon_detail_id)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_pokemon_detail_version_1
    }

    override fun getViewModel(): PokemonDetailViewModel {
        mPokemonDetailViewModel =
            ViewModelProvider(
                this,
                MyViewModelFactory()
            )
                .get(PokemonDetailViewModel::class.java)
        return mPokemonDetailViewModel
    }

    private fun checkIntents() {
        currentPokemon = intent.extras?.getParcelable(POKEMON_TO_SHOW)
        Timber.e("current Pokemon %s", currentPokemon?.name)

    }

    private fun setupUI() {
        setSupportActionBar(binding.pokeDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.pokeDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.pokemonDetailAppbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                {

                }
            });


        val pagerAdapter = PokemonDetailViewPagerAdapter(supportFragmentManager)

        binding.pokeDetailViewPager.apply {
            adapter = pagerAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {

                }

                override fun onPageScrollStateChanged(state: Int) {
                }

            })
        }

        binding.pokeDetailTabLayout.setupWithViewPager(binding.pokeDetailViewPager)
        //binding.pokeDetailTabLayout.getTabAt(0)?.setIcon(R.drawable.ic_home_news)

        val options: RequestOptions = RequestOptions()
            .error(R.drawable.img_spider)
        // show image + color the  background

        Glide.with(this)
            .load(currentPokemon?.getImageUrl())
            .apply(options)
            .into(binding.pokemonImage)
        /*
        .listener(
            GlidePalette.with(currentPokemon?.getImageUrl())
                .use(BitmapPalette.Profile.MUTED_LIGHT)
                .intoCallBack { palette ->
                    val light = palette?.lightVibrantSwatch?.rgb
                    val domain = palette?.dominantSwatch?.rgb
                    if (domain != null) {
                        if (light != null) {
                            Rainbow(binding.pokemonDetailToolbarLayout).palette {
                                +color(domain)
                                +color(light)
                            }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                        } else {
                            binding.pokemonDetailToolbarLayout.setBackgroundColor(domain)
                        }
                        window.apply {
                            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                            statusBarColor = domain
                        }
                    }
                }
                .crossfade(true))
         */


        binding.pokemonDetailAppbar.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(resources, R.color.material_amber_800, null)
        )

        getPokemonInfo()


    }

    private fun getPokemonInfo() {
        getViewModel().pokemonInfoLiveData.observe(this, Observer {

            // display stats
            /*
            for (stat in it?.stats!!) {
                when (stat.stat?.name){
                    "hp" -> {
                        val fullStat = stat.baseStat + stat.effort
                        binding.pokemonHpStatProgress.labelText = formatStat(fullStat)
                        binding.pokemonHpStatProgress.progress = (fullStat).toFloat()
                    }
                    "speed" ->{
                        val fullStat = stat.baseStat + stat.effort
                        binding.pokemonSpeedStatProgress.labelText = formatStat(fullStat)
                        binding.pokemonSpeedStatProgress.progress = (fullStat).toFloat()
                    }
                    "defense" ->{
                        val fullStat = stat.baseStat + stat.effort
                        binding.pokemonDefStatProgress.labelText = formatStat(fullStat)
                        binding.pokemonDefStatProgress.progress = (fullStat).toFloat()
                    }
                    "attack" ->{
                        val fullStat = stat.baseStat + stat.effort
                        binding.pokemonAtkStatProgress.labelText = formatStat(fullStat)
                        binding.pokemonAtkStatProgress.progress = (fullStat).toFloat()
                    }
                }
            }

             */
            // TODO : display types
            /*
            for (typeInfo in it.types!!) {

                with(binding.pokemonTypeListView) {
                    addRibbon(
                        ribbonView(context) {
                            setText(typeInfo.type!!.name)
                            setTextColor(Color.WHITE)
                            setPaddingLeft(84f)
                            setPaddingRight(84f)
                            setPaddingTop(2f)
                            setPaddingBottom(10f)
                            setTextSize(16f)
                            setRibbonRadius(120f)
                            setTextStyle(Typeface.BOLD)
                            setRibbonBackgroundColorResource(
                                PokemonTypeUtils.getTypeColor(typeInfo.type!!.name)
                            )
                        }.apply {
                            maxLines = 1
                            gravity = Gravity.CENTER
                        }
                    )
                }
            }

             */
        })
    }

    private fun formatStat(statValue: Int): String {
        return "$statValue/300"
    }

    private fun formatHeight(height: Int): String {
        return "$height M"
    }

    private fun formatWeight(weight: Int): String {
        return "$weight KG"
    }


    companion object {

        fun startActivityFromIntent(
            context: Context, transformationLayout: TransformationLayout,
            pokemon: PokemonItem
        ) {
            val detailIntent = Intent(context, PokemonDetailActivity::class.java)
            detailIntent.putExtra(POKEMON_TO_SHOW, pokemon)
            TransformationCompat.startActivity(transformationLayout, detailIntent)
        }
    }

}
