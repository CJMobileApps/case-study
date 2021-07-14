package com.target.targetcasestudy.ui.dagger

import com.target.targetcasestudy.dagger.DealsApplicationComponent
import com.target.targetcasestudy.data.service.DealsServiceI
import com.target.targetcasestudy.ui.dealslist.DealListFragment
import dagger.Component

@DealsScope
@Component(dependencies = [DealsApplicationComponent::class])
interface DealsComponent {

    fun provideDealsService(): DealsServiceI

    fun inject(fragment: DealListFragment)
}