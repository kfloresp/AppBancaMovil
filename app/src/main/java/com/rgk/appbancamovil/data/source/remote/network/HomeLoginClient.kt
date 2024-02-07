package com.rgk.appbancamovil.data.source.remote.network
import com.rgk.appbancamovil.data.source.remote.model.MessageResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeLoginClient{

    @GET("IniciarSesion")
    suspend fun getLogin(
        @Query("usr") usr: String,
        @Query("pass") pass: String
    ): Response<MessageResponseModel?>?

}