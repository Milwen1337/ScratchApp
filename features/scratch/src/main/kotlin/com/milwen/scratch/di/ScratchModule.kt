package com.milwen.scratch.di

import com.milwen.scratch.business.ActivationViewModel
import com.milwen.scratch.business.CardMainViewModel
import com.milwen.scratch.business.ScratchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scratchModule = module {
    viewModel { CardMainViewModel() }
    viewModel { ScratchViewModel() }
    viewModel { ActivationViewModel() }
}