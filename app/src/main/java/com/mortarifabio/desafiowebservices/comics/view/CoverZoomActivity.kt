package com.mortarifabio.desafiowebservices.comics.view

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.databinding.ActivityCoverZoomBinding
import com.mortarifabio.desafiowebservices.model.ComicsResult
import com.mortarifabio.desafiowebservices.utils.Constants.Intent.INTENT_KEY_COMIC


class CoverZoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoverZoomBinding
    private var comic: ComicsResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        binding = ActivityCoverZoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comic = intent.getParcelableExtra(INTENT_KEY_COMIC)
        loadImage()
        setupObservables()
    }

    private fun loadImage() = with(binding) {
        comic?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ivCoverZoom.transitionName = getString(R.string.cover_transition_name, it.id.toString())
                Log.i("transition zoom", ivCoverZoom.transitionName)
            }
            Glide.with(this@CoverZoomActivity)
                .asBitmap()
                .load(it.zoomImage)
                .fitCenter()
                .dontAnimate()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(ivCoverZoom)
        }
    }

    private fun setupObservables() = with(binding) {
        ibCoverBack.setOnClickListener {
            supportFinishAfterTransition()
        }
    }

}