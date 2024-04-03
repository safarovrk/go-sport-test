package com.example.gosporttest.di

import com.example.gosporttest.presentation.basket.BasketFragment
import com.example.gosporttest.presentation.profile.ProfileFragment
import com.example.gosporttest.presentation.menu.MenuFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun basketFragment(): BasketFragment

}