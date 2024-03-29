package com.soufianekre.pokebox.ui

import android.view.View
import android.view.WindowManager
import android.widget.GridView
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.progressview.ProgressView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import com.soufianekre.pokebox.R
import com.soufianekre.pokebox.data.models.PokemonItemType

object ViewBindingUtils {
    @JvmStatic
    @BindingAdapter("paletteImage", "paletteCard")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: CardView) {

        try {
            Glide.with(view.context)
                .load(url)
                .error(R.drawable.img_spider)
                .listener(
                    GlidePalette.with(url)
                        .use(BitmapPalette.Profile.MUTED_LIGHT)
                        .intoCallBack { palette ->
                            val rgb = palette?.dominantSwatch?.rgb
                            if (rgb != null) {
                                paletteCard.setCardBackgroundColor(rgb)
                            }
                        }.crossfade(true)
                ).into(view)

        } catch (e: Exception) {
            Glide.with(view.context)
                .load(url)
                .error(R.drawable.img_spider)
                .into(view)

            paletteCard.setCardBackgroundColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.material_green_500
                )
            )

        }


    }

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePaletteView(view: AppCompatImageView, url: String, paletteView: View) {
        val context = view.context
        Glide.with(context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        val domain = palette?.dominantSwatch?.rgb
                        if (domain != null) {
                            if (light != null) {
                                Rainbow(paletteView).palette {
                                    +color(domain)
                                    +color(light)
                                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
                            } else {
                                paletteView.setBackgroundColor(domain)
                            }
                            if (context is AppCompatActivity) {
                                context.window.apply {
                                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                                    statusBarColor = domain
                                }
                            }
                        }
                    }.crossfade(true)
            ).into(view)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("onBackPressed")
    fun bindOnBackPressed(view: View, onBackPress: Boolean) {
        val context = view.context
        if (onBackPress && context is OnBackPressedDispatcherOwner) {
            view.setOnClickListener {
                context.onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bindPokemonTypes")
    fun bindPokemonTypes(recyclerView: GridView, types: List<PokemonItemType>?) {

    }

    @JvmStatic
    @BindingAdapter("progressView_labelText")
    fun bindProgressViewLabelText(progressView: ProgressView, text: String?) {
        progressView.labelText = text
    }

    @JvmStatic
    @BindingAdapter("progressView_progress")
    fun bindProgressViewProgress(progressView: ProgressView, value: Int?) {
        if (value != null) {
            progressView.progress = value.toFloat()
        }
    }

    @JvmStatic
    @BindingAdapter("progressView_max")
    fun bindProgressViewMax(progressView: ProgressView, value: Int?) {
        if (value != null) {
            progressView.max = value.toFloat()
        }
    }

}
