package com.rgk.appbancamovil.feature.home_login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.appbancamovil.data.common.Constants.ERROR_OK
import com.rgk.appbancamovil.data.common.MessageDTO
import com.rgk.appbancamovil.domain.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeLoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    val loadLoginMobile = MutableLiveData<Boolean>()
    val resultLoginMobile = MutableLiveData<MessageDTO?>()
    val errorLoginMobile = MutableLiveData<MessageDTO?>()

    fun loginMobile(user: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loadLoginMobile.postValue(true)
            val result = getLoginUseCase(user, password)
            if (result.errNumber == ERROR_OK) {
                resultLoginMobile.postValue(result)
            } else {
                errorLoginMobile.postValue(result)
            }
            loadLoginMobile.postValue(false)
        }
    }
}