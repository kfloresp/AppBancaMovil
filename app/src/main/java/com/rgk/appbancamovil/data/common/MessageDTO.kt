package com.rgk.appbancamovil.data.common

import com.google.gson.annotations.SerializedName

data class MessageDTO(
    @SerializedName("errNumber")
    var errNumber: Int? = null,
    @SerializedName("message")
    var errMessage: String? = "",
    @SerializedName("valor1")
    var valueInt: Int? = null,
    @SerializedName("valor2")
    var valueString: String = "",
    @SerializedName("valor3")
    var valueDecimal: Double = 0.0
)