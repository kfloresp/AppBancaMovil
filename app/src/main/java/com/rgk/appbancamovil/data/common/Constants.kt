package com.rgk.appbancamovil.data.common

object Constants {
    const val ERROR_OK = 0
    const val MSG_ERROR_OK = "OK."

    const val ERROR_GREAT = 1
    const val ERROR_INFO = 2
    const val ERROR_FATAL = 3
    const val ERROR_WARNING = 4

    const val COD_ERROR_BODY_NULL = 5
    const val MSG_ERROR_BODY_NULL = "El contenido del cuerpo es nulo.\n"

    const val COD_ERROR_RESPONSE_NULL = 6
    const val MSG_ERROR_RESPONSE_NULL = "La respuesta del servicio es incorrecta o nula."

    const val COD_ERROR_NOT_INTERNET = 7
    const val MSG_ERROR_NOT_INTERNET = "No se puede acceder a internet."

    const val COD_ERROR_LIST_NULL = 8
    const val MSG_ERROR_LIST_NULL = "Listado eso incorrecto o nulo."

    const val COD_ERROR_NO_LOGIN = 9
    const val MSG_ERROR_NO_LOGIN = "Usuario y/o contraseña incorrectos"

    const val COD_ERROR_LIST_EMPY = 10
    const val MSG_ERROR_LIST_EMPY = "No se encontraron resultados en la búsqueda."

    const val COD_ERROR_NO_RESULT = 11
    const val MSG_ERROR_NO_RESULT = "No se encontraron resultados."

    const val MSG_ERROR_NO_PAGE = "Error: No se encontraron páginas y totales (valores nulos)."
    const val MSG_ERROR_CONEXION = "El servidor no pudo ser alcanzado, por lo tanto, no se logró establecer conexión."

    const val COD_ERROR_EXCEPTION = 999
    const val MSG_ERROR_EXCEPTION = "Error Excp: "

    const val NO_AUTHORIZED: String = "Usuario y/o contraseña incorrectos."

}