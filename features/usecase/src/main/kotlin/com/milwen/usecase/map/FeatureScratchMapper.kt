package com.milwen.usecase.map

import com.milwen.database.data.entity.ScratchCardEntity
import com.milwen.database.data.enum.ScratchCardEntityStatus
import com.milwen.scratch.data.ScratchState
import com.milwen.scratch.data.ScratchCard

object FeatureScratchMapper {

    fun ScratchCardEntity.toScratchCard(): ScratchCard =
        when (status) {
            ScratchCardEntityStatus.UNSCRATCHED ->
                ScratchCard(ScratchState.Unscratched)
            ScratchCardEntityStatus.SCRATCHED ->
                ScratchCard(ScratchState.Scratched(revealCode.orEmpty()))
            ScratchCardEntityStatus.ACTIVATED ->
                ScratchCard(ScratchState.Activated)
        }
}