package com.mortarifabio.desafiowebservices.comics.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mortarifabio.desafiowebservices.R
import com.mortarifabio.desafiowebservices.databinding.ComicsItemBinding
import com.mortarifabio.desafiowebservices.model.ComicsResult
import com.mortarifabio.desafiowebservices.model.ComicsResult.Companion.DIFF_CALLBACK

class ComicsAdapter(
    private val onItemClicked: (ComicsResult?) -> Unit
) : PagedListAdapter<ComicsResult, ComicsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ComicsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class ViewHolder(
        private val binding: ComicsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: ComicsResult?, onItemClicked: (ComicsResult?) -> Unit) = with(binding) {
            comic?.let {
                Glide.with(itemView.context)
                    .load(comic.image)
                    .placeholder(R.drawable.bg_placeholder)
                    .fitCenter()
                    .into(ivComicsImage)
                tvComicsIssueNumber.text = comic.issueNumber
                ivComicsImage.setOnClickListener {
                    onItemClicked(comic)
                }
            }
        }
    }
}