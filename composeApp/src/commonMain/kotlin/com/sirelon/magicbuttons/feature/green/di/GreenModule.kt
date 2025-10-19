package com.sirelon.magicbuttons.feature.green.di

import com.sirelon.magicbuttons.feature.green.GreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val greenModule = module {

    viewModelOf(::GreenViewModel)

}