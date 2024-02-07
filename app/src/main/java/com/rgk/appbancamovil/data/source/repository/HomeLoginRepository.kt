package com.rgk.appbancamovil.data.source.repository

import com.rgk.appbancamovil.data.common.Constants
import com.rgk.appbancamovil.data.common.MessageDTO
import com.rgk.appbancamovil.data.source.local.DataAccountEntity
import com.rgk.appbancamovil.data.source.local.DataAccountLocal
import com.rgk.appbancamovil.data.source.remote.service.HomeLoginService
import com.rgk.appbancamovil.util.Constants.timeDelay
import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeLoginRepository @Inject constructor(
    private val service: HomeLoginService,
    private val local:DataAccountLocal
) {
    suspend fun getLogin(usr: String, pass: String): MessageDTO {
        delay(timeDelay)

        val authorizedAccounts = local.getAuthorizedAccounts()

        val matchingAccount = authorizedAccounts.find {
            it.user == usr && it.password == pass
        }

        return if (matchingAccount != null) {
            service.getLogin(usr, pass)
        } else {
            MessageDTO(errNumber = Constants.ERROR_INFO, errMessage = Constants.NO_AUTHORIZED)
        }
    }
}