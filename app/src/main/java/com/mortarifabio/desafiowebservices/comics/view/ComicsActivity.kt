package com.mortarifabio.desafiowebservices.comics.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.comics.view.adapter.ComicsAdapter
import com.mortarifabio.desafiowebservices.comics.viewModel.ComicsViewModel
import com.mortarifabio.desafiowebservices.databinding.ActivityComicsBinding
import com.mortarifabio.desafiowebservices.databinding.ComicsItemBinding
import com.mortarifabio.desafiowebservices.utils.Constants.Intent.INTENT_KEY_COMIC

class ComicsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComicsBinding
    private val viewModel: ComicsViewModel by lazy {
        ViewModelProvider(this).get(ComicsViewModel::class.java)
    }
    private val comicsAdapter : ComicsAdapter by lazy{
        ComicsAdapter{
            it?.let {
                val itemBinding = ComicsItemBinding.inflate(layoutInflater)
                val intent = Intent(this, ComicDetailsActivity::class.java)
                intent.putExtra(INTENT_KEY_COMIC, it)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ComicsActivity,
                    Pair.create(
                        itemBinding.ivComicsImage,
                        getString(R.string.cover_transition_name, it.id.toString())
                    )
                )
                startActivity(intent, options.toBundle())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {
        binding.rvComics.apply {
            layoutManager = GridLayoutManager(this@ComicsActivity, 3)
            adapter = comicsAdapter
        }
    }

    private fun loadData() {
        viewModel.comicsPagedList?.observe(this){ pagedList ->
            comicsAdapter.submitList(pagedList)
        }
    }
}