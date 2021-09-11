package com.johnsapps.liverpoolsearching.search.data.repository

import com.johnsapps.liverpoolsearching.data.model.GenericResponse
import com.johnsapps.liverpoolsearching.search.data.model.PlpResponse

interface SearchRepository {
    suspend fun getPlp(search:String, page:Int):GenericResponse<PlpResponse>
}