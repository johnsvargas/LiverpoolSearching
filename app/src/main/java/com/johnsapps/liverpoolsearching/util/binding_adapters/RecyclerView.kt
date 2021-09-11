package com.johnsapps.liverpoolsearching.util.binding_adapters

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johnsapps.liverpoolsearching.search.data.model.Product

fun RecyclerView.addOnScrollListenerPagination(mLayoutManager : LinearLayoutManager,loader:Boolean,action:() -> Unit){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) { //check for scroll down
                val visibleItemCount = mLayoutManager.childCount
                val totalItemCount = mLayoutManager.itemCount
                val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                if (loader) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                       action.invoke()
                    }
                }
            }
        }
    })
}
