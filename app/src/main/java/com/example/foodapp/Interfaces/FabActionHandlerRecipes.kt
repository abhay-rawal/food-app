package com.example.foodapp.Interfaces

import com.example.foodapp.DataClass.Recipe

interface FabActionHandlerRecipes {
    fun onFabClicked(recipe: Recipe)
}