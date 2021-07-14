package com.target.targetcasestudy.dagger

import com.target.targetcasestudy.DealsApplication
import com.target.targetcasestudy.dagger.module.ContextModule
import com.target.targetcasestudy.dagger.module.DataModule
import com.target.targetcasestudy.dagger.module.NetworkModule
import com.target.targetcasestudy.data.service.DealsServiceI
import dagger.Component


@DealsApplicationScope
@Component(modules = [ContextModule::class, NetworkModule::class, DataModule::class])
interface DealsApplicationComponent {

    fun provideDealsService(): DealsServiceI

    fun injectDealsApplicationComponent(dealsApplication: DealsApplication)
}