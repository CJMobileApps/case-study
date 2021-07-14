package com.target.targetcasestudy.network

import com.target.targetcasestudy.data.models.Deals
import io.reactivex.Single
import retrofit2.http.GET

interface DealsApi {

//    mobile_case_study_deals/v1/swagger-ui/index.html?url=/mobile_case_study_deals/v1/openapi.json
    //@GET("mobile_case_study_deals/v1")
    @GET("https://api.target.com/mobile_case_study_deals/v1/deals")
    fun getDeals(): Single<Deals>
}