package com.example.gosporttest.di

import android.content.Context
import com.example.gosporttest.TheApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: TheApplication): Context {
        return app.applicationContext
    }
}