package com.rgk.appbancamovil.domain.mapper

import com.rgk.appbancamovil.data.source.remote.model.MessageResponseModel
import com.rgk.appbancamovil.domain.model.MessageResponse

fun MessageResponseModel.toDomain() = MessageResponse(code, message)