package com.example.foodapp.DataClass

import android.net.Uri

data class Recipe(
    val title: String,
    val ingredients: String,
    val instruction: String,
    val imageUri: Uri?,
    val rating: Float
)
