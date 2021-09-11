package com.johnsapps.liverpoolsearching.search.data.repository

import com.johnsapps.liverpoolsearching.data.model.GenericResponse
import com.johnsapps.liverpoolsearching.search.data.model.PlpResponse
import com.johnsapps.liverpoolsearching.util.common.NetworkClient
import com.johnsapps.liverpoolsearching.util.common.ServiceApi
import retrofit2.create

class SearchRepositoryImpl:SearchRepository {
    private val service = NetworkClient.buildRetrofitClient().create<ServiceApi>()
    override suspend fun getPlp(search: String, page: Int): GenericResponse<PlpResponse> {
      return service.getPlp(search,page)
    }
}