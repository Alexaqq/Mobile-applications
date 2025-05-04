package com.example.lab_3.data.model

import androidx.annotation.DrawableRes

data class Place(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val imageRes: Int
)
