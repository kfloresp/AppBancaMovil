package com.rgk.appbancamovil.data.source.remote.network

import com.rgk.appbancamovil.data.source.remote.model.DataAccountModel
import com.rgk.appbancamovil.data.common.DataResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface HomeAccountsClient {

    @GET("actualizarCuentas")
    suspend fun getUpdateAccounts(
    ): Response<DataResponseDTO<DataAccountModel>?>?

    @GET("obtenerCuentas")
    suspend fun getAccounts(
    ): Response<DataResponseDTO<DataAccountModel>?>?

}