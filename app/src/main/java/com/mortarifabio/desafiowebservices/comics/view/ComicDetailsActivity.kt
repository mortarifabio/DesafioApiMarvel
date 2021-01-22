package com.mortarifabio.desafiowebservices.comics.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.bumptech.glide.Glide
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.databinding.ActivityComicDetailsBinding
import com.mortarifabio.desafiowebservices.model.ComicsResult
import com.mortarifabio.desafiowebservices.utils.Constants.Intent.INTENT_KEY_COMIC

class ComicDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicDetailsBinding
    private var comic: ComicsResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comic = intent.getParcelableExtra(INTENT_KEY_COMIC)
        setupComic()
        setupObservables()
    }

    private fun setupComic() = with(binding) {
        comic?.let {
            Glide.with(this@ComicDetailsActivity)
                .load(it.image)
                .placeholder(R.drawable.bg_placeholder)
                .fitCenter()
                .into(ivComicCover)
            it.banner?.let { banner ->
                Glide.with(this@ComicDetailsActivity)
                    .load(banner)
                    .centerCrop()
                    .into(ivComicBanner)
            }
            tvComicTitle.text = it.title
            tvComicDescription.text = it.description
            tvComicPublished.text = it.published
            tvComicPrice.text = it.price
            tvComicPages.text = it.pageCount.toString()

        }
    }

    private fun setupObservables() = with(binding) {
        ibComicBack.setOnClickListener {
            finish()
        }
        ivComicCover.setOnClickListener {
            val intent = Intent(this@ComicDetailsActivity, CoverZoomActivity::class.java)
            intent.putExtra(INTENT_KEY_COMIC, comic)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@ComicDetailsActivity,
                Pair.create(
                    ivComicCover,
                    getString(R.string.cover_transition_name, comic?.id.toString())
                )
            )
            startActivity(intent, options.toBundle())
        }
    }

}