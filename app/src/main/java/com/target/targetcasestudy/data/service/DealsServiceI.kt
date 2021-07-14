package com.target.targetcasestudy.data.service

import com.target.targetcasestudy.data.models.Deals
import io.reactivex.Single

interface DealsServiceI {

    fun getDeals(): Single<Deals>
}