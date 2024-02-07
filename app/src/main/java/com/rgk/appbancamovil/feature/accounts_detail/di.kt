package com.rgk.appbancamovil.feature.accounts_detail

import androidx.lifecycle.SavedStateHandle
import com.rgk.appbancamovil.domain.model.DataAccount
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class AccountsDetailModule{
    @Provides
    @Named("DataAccount")
    fun itemDataAccountProvider(stateHandle: SavedStateHandle): DataAccount =
        stateHandle.get<DataAccount>(AccountsDetailActivity.CLAVE_ITEM)
            ?: throw IllegalStateException("not found in the state handle")
}