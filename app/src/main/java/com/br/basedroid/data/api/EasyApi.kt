package com.br.basedroid.data.api

import com.br.basedroid.data.model.SimulationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EasyApi {

    @GET("v3/ecfaebf5-782b-4b24-ae4f-23b5c3a861da")
    suspend fun getSimulation(
        @Query("investedAmount") investedAmount: Int,
        @Query("index") index: String = "CDI",
        @Query("rate") rate: Int,
        @Query("isTaxFree") isTaxFree: Boolean = false,
        @Query("maturityDate") maturityDate: String
    ): SimulationResponse
}
