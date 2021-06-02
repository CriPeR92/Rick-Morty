package com.example.rickmortyapi.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickmortyapi.models.SessionData
import com.example.rickmortyapi.repository.CharactersRepository.getCharactersUpdate
import com.squareup.picasso.Picasso

@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .into(view)
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {

    this.run {

        val layoutManager: StaggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        this.layoutManager =  layoutManager
        this.setHasFixedSize(true)
        this.adapter = adapter

        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                var firstVisibleItems: IntArray? = null
                val visibleItemCount = layoutManager.childCount
                firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                val total = adapter.itemCount

                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    SessionData.pastVisibleItems = firstVisibleItems[0]
                }

                if (!SessionData.isLoading) {
                    if ((visibleItemCount + SessionData.pastVisibleItems) >= total) {
                        SessionData.page += 1
                        getCharactersUpdate(recyclerView)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}

@BindingAdapter("app:hideIfSaved")
fun hideIfSaved(view: View, number: Int) {
    view.visibility = if (number == 0) View.GONE else View.VISIBLE
}