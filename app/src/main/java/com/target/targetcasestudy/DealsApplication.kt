package com.target.targetcasestudy

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.dagger.DaggerDealsApplicationComponent
import com.target.targetcasestudy.dagger.DealsApplicationComponent
import com.target.targetcasestudy.dagger.module.ContextModule

class DealsApplication : Application() {

    lateinit var dealsApplicationComponent: DealsApplicationComponent

    companion object {

        fun get(activity: Activity): DealsApplication {
            return activity.application as DealsApplication
        }

        fun get(context: Context): DealsApplication {
            return context.applicationContext as DealsApplication
        }

        fun get(fragment: Fragment): DealsApplication {
            return fragment.activity?.application as DealsApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        dealsApplicationComponent = DaggerDealsApplicationComponent.builder().contextModule(
            ContextModule(this)).build()

        dealsApplicationComponent.injectDealsApplicationComponent(this)

        if (BuildConfig.DEBUG) {
            //TODO add timber here
        }
    }
}
