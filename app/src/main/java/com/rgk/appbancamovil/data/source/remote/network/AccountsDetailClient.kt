package com.rgk.appbancamovil.data.source.remote.network
import com.rgk.appbancamovil.data.source.remote.model.DataMotionModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountsDetailClient {
    @GET("obtenerMovimientos/{id}")
    suspend fun getMoves(
        @Path("id") id: Int
    ): Response<DataResponseDTO<DataMotionModel>?>?
}