package com.example.micuadricula.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Topic (
    @StringRes val stringResourceId: Int,
    val points: Int,
    @DrawableRes val imageResourceId: Int
)