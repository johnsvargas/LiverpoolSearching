package com.johnsapps.liverpoolsearching.data.model

data class GenericResponse<T>(
    val status:StatusResponseLiverpool,
    val pageType:String,
    val plpResults: T
)

data class StatusResponseLiverpool(
    val status:String,
    val statusCode:Int
)