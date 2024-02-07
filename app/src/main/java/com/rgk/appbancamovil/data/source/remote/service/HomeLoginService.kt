package com.rgk.appbancamovil.data.source.remote.service

import android.content.Context
import com.rgk.appbancamovil.data.common.Constants
import com.rgk.appbancamovil.data.common.MessageDTO
import com.rgk.appbancamovil.data.common.NetworkUtil
import com.rgk.appbancamovil.data.source.remote.network.HomeLoginClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeLoginService @Inject constructor(
    private val api : HomeLoginClient,
    @ApplicationContext private val context: Context
) {
    suspend fun getLogin(usr:String,pass:String): MessageDTO {
        return withContext(Dispatchers.IO) {
            val result = MessageDTO()
            if (NetworkUtil.isThereInternetConnection(context)) {
                try {
                    val response = api.getLogin(usr = usr, pass = pass)
                    if (response != null) {
                        val body = response.body()
                        if (body != null) {
                            result.apply {
                                this.errNumber = body.code
                                this.errMessage = body.message
                            }
                        } else {
                            result.apply {
                                errNumber = Constants.COD_ERROR_BODY_NULL
                                errMessage =  Constants.MSG_ERROR_CONEXION+ "\n Motivo-> ResponseCode:" + response.code() + " ResponseMessage:" + response.message()
                            }
                        }
                    } else {
                        result.apply {
                            errNumber = Constants.COD_ERROR_RESPONSE_NULL
                            errMessage = Constants.MSG_ERROR_CONEXION
                        }
                    }
                } catch (e: Exception) {
                    result.apply {
                        errNumber = Constants.COD_ERROR_EXCEPTION
                        errMessage = Constants.MSG_ERROR_CONEXION
                    }
                }
            } else {
                result.apply {
                    errNumber = Constants.COD_ERROR_NOT_INTERNET
                    errMessage = Constants.MSG_ERROR_NOT_INTERNET
                }
            }
            return@withContext result
        }
    }
}