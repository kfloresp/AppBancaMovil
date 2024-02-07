package com.rgk.appbancamovil.domain.mapper

import com.rgk.appbancamovil.data.source.remote.model.DataAccountModel
import com.rgk.appbancamovil.domain.model.DataAccount

fun DataAccountModel.toDomain() = DataAccount(id, account, currency, urlImage, balanceAmount, accountNumber)