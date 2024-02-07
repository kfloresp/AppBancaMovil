package com.rgk.appbancamovil.data.common

import com.google.gson.annotations.SerializedName

data class DataResponseDTO<T>(
    @SerializedName("code") var code: Int?=null,
    @SerializedName("data") var data: List<T>?=null,
    @SerializedName("messageDTO") var messageDTO: MessageDTO?=MessageDTO()
)