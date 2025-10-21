package com.sirelon.magicbuttons.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import magicbuttons.composeapp.generated.resources.PTSans_Bold
import magicbuttons.composeapp.generated.resources.PTSans_Italic
import magicbuttons.composeapp.generated.resources.PTSans_Regular
import magicbuttons.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

object Typography {

    @Composable
    fun ptSansFonts() = FontFamily(
        Font(Res.font.PTSans_Regular, weight = FontWeight.Normal),
        Font(Res.font.PTSans_Italic, weight = FontWeight.Normal, style = FontStyle.Italic),
        Font(Res.font.PTSans_Bold, weight = FontWeight.Bold),
        Font(Res.font.PTSans_Bold, weight = FontWeight.Bold, style = FontStyle.Italic)
    )
}