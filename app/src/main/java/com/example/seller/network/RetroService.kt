package com.example.seller.network

import com.example.seller.models.ListingDataModel
import retrofit2.http.GET

interface RetroService {

    @GET("default/dynamodb-writer")
    suspend fun getApiData(): ListingDataModel
}