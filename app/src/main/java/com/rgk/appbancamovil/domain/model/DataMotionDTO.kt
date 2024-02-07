package com.rgk.appbancamovil.domain.model

data class DataMotion(val operationDescription:String?,
                           val operationDate:String?,
                           val operationNumber:Int?,
                           val operationAmount:Float?,
                           val operationCurrency:String?,
                           val operationType:Int?
)