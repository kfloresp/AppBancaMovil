package com.rgk.appbancamovil.data.source.local

import javax.inject.Inject

class DataAccountLocal  @Inject constructor(
) {
    fun getAuthorizedAccounts(): List<DataAccountEntity> {
        val user1 = DataAccountEntity("userTest1", "passTest1")
        val user2 = DataAccountEntity("User@test", "TestPass_")
        val user3 = DataAccountEntity("user123&", "123456")
        return listOf(user1, user2, user3)
    }
}
