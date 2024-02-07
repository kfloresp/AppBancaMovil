package com.rgk.appbancamovil.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class MessageResponseModel(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String?
)