package com.milwen.scratch.di

import com.milwen.restapi.data.repository.ActivationRepositoryImpl
import com.milwen.scratch.domain.ActivationRepository
import com.milwen.scratch.business.ActivationViewModel
import com.milwen.scratch.business.CardMainViewModel
import com.milwen.scratch.business.ScratchViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val scratchModule = module {
    viewModel { CardMainViewModel() }
    viewModel { ScratchViewModel() }
    viewModel { ActivationViewModel(get()) }
}