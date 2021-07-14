package com.target.targetcasestudy.data.service

import com.target.targetcasestudy.network.DealsApi

class DealsService(
    private val dealsApi: DealsApi
) : DealsServiceI{

    override fun getDeals() = dealsApi.getDeals()
}