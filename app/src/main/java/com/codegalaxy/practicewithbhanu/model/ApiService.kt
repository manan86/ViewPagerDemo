package com.codegalaxy.practicewithbhanu.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("objects")
    suspend fun saveDataToAPI(
        @Body apr1 : DataRequest
    ) : Response<DataResponse>

}