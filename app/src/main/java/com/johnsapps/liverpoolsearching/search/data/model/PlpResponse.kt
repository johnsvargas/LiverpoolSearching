package com.johnsapps.liverpoolsearching.search.data.model

import android.util.Log

data class PlpResponse (
    val plpState: PlpState,
    val records:ArrayList<Product>
    )

data class PlpState(
    val categoryId:String,
    val currentSortOption:String,
    val currentFilters:String,
    val firstRecNum:Int,
    val lastRecNum:Int,
    val recsPerPage:Int,
    val totalNumRecs:Int,
    val originalSearchTerm:String
){
    val isHaveNextPage:Boolean
        get() {
            return lastRecNum < totalNumRecs
    }
}