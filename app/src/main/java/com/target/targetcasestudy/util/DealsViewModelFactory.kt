package com.target.targetcasestudy.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.data.service.DealsServiceI
import com.target.targetcasestudy.ui.dealslist.DealListViewModel
import java.lang.IllegalArgumentException

class DealsViewModelFactory(
    private val dealsService: DealsServiceI
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DealListViewModel::class.java) -> {
                DealListViewModel(dealsService) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}