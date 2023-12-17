package com.example.foodapp.DataClass

import java.util.Calendar

data class MealPlannerDataClass(
    val date: Calendar,
    val meal: String
) {
    val weekday: Int
        get() = date.get(Calendar.DAY_OF_WEEK)
}