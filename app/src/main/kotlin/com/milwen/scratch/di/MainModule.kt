package com.milwen.scratch.di

import com.milwen.scratch.business.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
    single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.IO) }
}