package com.rgk.appbancamovil.data.source.remote.model

import com.google.gson.annotations.SerializedName


data class DataMotionModel(@SerializedName("operationDescription") val operationDescription:String?,
                            @SerializedName("operationDate")val operationDate:String?,
                            @SerializedName("operationNumber")val operationNumber:Int?,
                            @SerializedName("operationAmount")val operationAmount:Float?,
                            @SerializedName("operationCurrency")val operationCurrency:String?,
                            @SerializedName("operationType")val operationType:Int?
)