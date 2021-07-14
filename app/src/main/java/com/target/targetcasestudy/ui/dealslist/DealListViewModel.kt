package com.target.targetcasestudy.ui.dealslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.models.Deals
import com.target.targetcasestudy.data.service.DealsService
import com.target.targetcasestudy.data.service.DealsServiceI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DealListViewModel(private val dealsService: DealsServiceI): ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val dealsMutableLiveData = MutableLiveData<Deals>()
    val dealsLiveData = dealsMutableLiveData

    init {
        Log.d("HERE_","DealsListViewodel ")
        compositeDisposable.add(getDeals())
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun getDeals(): Disposable {

        return dealsService.getDeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ deals ->
                dealsMutableLiveData.value = deals
                Log.d("HERE_","deals " + deals.products)
            }, { error ->
                Log.d("HERE_","error " + error)
            })

    }


}