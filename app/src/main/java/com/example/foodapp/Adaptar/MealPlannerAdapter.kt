package com.example.foodapp.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.DataClass.MealPlannerDataClass
import com.example.foodapp.DataClass.Recipe
import com.example.foodapp.databinding.MealplannerListBinding
import com.example.foodapp.databinding.RecepieListBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MealPlannerAdapter(private val blist: ArrayList<MealPlannerDataClass>) : RecyclerView.Adapter<MealPlannerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MealplannerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recepie_holder = blist[position]
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        holder.binding.date.text = dateFormat.format(recepie_holder.date.time)
        holder.binding.weekday.text = dayOfWeekToString(recepie_holder.weekday)
        holder.binding.meal.text = recepie_holder.meal
    }

    private fun dayOfWeekToString(dayOfWeek: Any): String  {
        return when(dayOfWeek) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Unknown"
        }
    }

    override fun getItemCount(): Int {
        return blist.size
    }

    inner class MyViewHolder(val binding: MealplannerListBinding) : RecyclerView.ViewHolder(binding.root)
}