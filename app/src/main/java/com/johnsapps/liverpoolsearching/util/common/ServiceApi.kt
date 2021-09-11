package com.johnsapps.liverpoolsearching.util.common

import com.johnsapps.liverpoolsearching.data.model.GenericResponse
import com.johnsapps.liverpoolsearching.search.data.model.PlpResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("plp")
    suspend fun getPlp(@Query("search-string") search:String , @Query("page-number") page:Int ):GenericResponse<PlpResponse>
}