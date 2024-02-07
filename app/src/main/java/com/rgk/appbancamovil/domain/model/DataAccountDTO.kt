package com.rgk.appbancamovil.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataAccount(val id:Int,
                            val account:String?,
                            val currency:String?,
                            val urlImage:String?,
                            val balanceAmount:Float?,
                            val accountNumber:String?
):Parcelable