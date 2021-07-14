package com.target.targetcasestudy.dagger.module

import com.target.targetcasestudy.dagger.DealsApplicationScope
import com.target.targetcasestudy.data.service.DealsService
import com.target.targetcasestudy.data.service.DealsServiceI
import com.target.targetcasestudy.network.DealsApi
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @DealsApplicationScope
    @Provides
    fun dealsService(
        dealsApi: DealsApi
    ): DealsServiceI {
        return DealsService(dealsApi)
    }
}
