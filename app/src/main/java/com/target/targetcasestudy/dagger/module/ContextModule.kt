package com.target.targetcasestudy.dagger.module

import android.content.Context
import com.target.targetcasestudy.dagger.DealsApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {
    private val context = context.applicationContext

    @DealsApplicationScope
    @Provides
    fun context(): Context {
        return context
    }


}