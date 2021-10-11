package com.soufianekre.pokebox.ui.pokemon_detail

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.appbar.AppBarLayout
import com.skydoves.androidribbon.ribbonView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.soufianekre.pokebox.MyViewModelFactory
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItem
import com.soufianekre.pokebox.databinding.ActivityPokemonDetailBinding
import com.soufianekre.pokebox.helper.PokemonTypeUtils
import com.soufianekre.pokebox.ui.base.BaseTransformationActivity
import com.soufianekre.pokebox.ui.main.pokemon_list.PokemonListFragment.Companion.POKEMON_TO_SHOW
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import timber.log.Timber


class PokemonDetailActivity :
    BaseTransformationActivity<ActivityPokemonDetailBinding, PokemonDetailViewModel>() {

    private lateinit var mPokemonDetailViewModel: PokemonDetailViewModel
    private lateinit var binding: ActivityPokemonDetailBinding

    private var currentPokemon: PokemonItem? = PokemonItem()
    private var menuPokemonIdItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIntents()
        binding = getViewDataBinding()
        binding.apply {
            vm = getViewModel()
            pokemon = currentPokemon
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

        menuPokemonIdItem = menu!!.findItem(R.id.menu_pokemon_detail_id)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_pokemon_detail
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
        setSupportActionBar(binding.pokemonDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.pokemonDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        binding.pokemonDetailAppbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                run {

                    if (verticalOffset == 0 || verticalOffset <= binding.pokemonDetailToolbar.height) {
                        binding.pokemonImage.visibility = View.VISIBLE
                    } else {
                        binding.pokemonImage.visibility = View.VISIBLE
                    }


                }
            });
        binding.pokemonName.text = currentPokemon?.name


        rotatePokeball()
        val options: RequestOptions = RequestOptions()
            .error(R.drawable.img_pokemon_chikorita)
        Glide.with(this)
            .load(currentPokemon?.getImageUrl())
            .apply(options)

            .listener(
                GlidePalette.with(currentPokemon?.getImageUrl())
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                            if (light != null) {
                                Rainbow(binding.pokemonDetailMainLayout).palette {
                                    +color(domain)
                                    +color(light)
                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
                                binding.pokemonDetailMainLayout.setBackgroundColor(domain)
                            }
                            window.apply {
                                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                                statusBarColor = domain
                            }
                        }
                    }
                    .crossfade(true))
            .into(binding.pokemonImage)


        /*
        binding.pokemonDetailAppbar.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(resources, R.color.material_amber_800, null)
        )

         */

        getPokemonInfo()


    }

    private fun getPokemonInfo() {
        getViewModel().pokemonInfoLiveData.observe(this, Observer {

            // Setup View Pager

            menuPokemonIdItem?.title = String.format("#%03d", it?.pokemonId)

            val pagerAdapter = PokemonDetailViewPagerAdapter(supportFragmentManager, it)

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


            // TODO : display types

            if (it != null) {
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


            }


        })
    }

    private fun rotatePokeball() {
        val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        rotation.fillAfter = true
        binding.pokemonPokeballImg.startAnimation(rotation)
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
