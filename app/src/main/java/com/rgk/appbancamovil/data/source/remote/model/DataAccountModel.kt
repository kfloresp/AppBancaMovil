package com.rgk.appbancamovil.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class DataAccountModel(@SerializedName("id") val id:Int,
                            @SerializedName("account")val account:String?,
                            @SerializedName("currency")val currency:String?,
                            @SerializedName("urlImage")val urlImage:String?,
                            @SerializedName("balanceAmount")val balanceAmount:Float?,
                            @SerializedName("accountNumber")val accountNumber:String?
)