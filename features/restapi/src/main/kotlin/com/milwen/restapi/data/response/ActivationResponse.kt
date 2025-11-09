package com.milwen.restapi.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivationResponse(
    val android: Int,
)