package com.milwen.usecase.di

import com.milwen.scratch.domain.ScratchCardObserveUseCase
import com.milwen.scratch.domain.ScratchUseCase
import com.milwen.usecase.scratch.ScratchCardObserveUseCaseImpl
import com.milwen.usecase.scratch.ScratchUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<ScratchCardObserveUseCase> { ScratchCardObserveUseCaseImpl(get()) }
    single<ScratchUseCase> { ScratchUseCaseImpl(get()) }
}