package com.rgk.appbancamovil.domain.mapper

import com.rgk.appbancamovil.data.source.remote.model.DataMotionModel
import com.rgk.appbancamovil.domain.model.DataMotion

fun DataMotionModel.toDomain() = DataMotion(operationDescription, operationDate, operationNumber, operationAmount, operationCurrency, operationType)