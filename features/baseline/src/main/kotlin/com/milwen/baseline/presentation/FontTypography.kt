package com.milwen.baseline.presentation

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.milwen.scratch.baseline.R

private val fontsOnAir = FontFamily(
    Font(R.font.onair_regular, FontWeight.Normal),
    Font(R.font.onair_bold, FontWeight.Bold),
)

private object Titles {
    val titleS = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    )
    val titleM = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    )
    val titleL = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    )
    val titleXL = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp
    )
}

private object Texts {
    val textSSemi = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp
    )
    val textSRegular = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 11.sp
    )
    val textMSemi = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    )
    val textMRegular = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
    val textLSemi = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    val textLRegular = TextStyle(
        fontFamily = fontsOnAir,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    )
}

val AppTypography = Typography(
    titleLarge = Titles.titleL,
    titleMedium = Titles.titleM,
    titleSmall = Titles.titleS,
    displayLarge = Titles.titleXL,
    bodyLarge = Texts.textLSemi,
    bodyMedium = Texts.textMSemi,
    bodySmall = Texts.textSSemi,
    labelLarge = Texts.textLRegular,
    labelMedium = Texts.textMRegular,
    labelSmall = Texts.textSRegular
)