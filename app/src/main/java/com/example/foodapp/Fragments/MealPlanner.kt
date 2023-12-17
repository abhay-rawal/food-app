package com.example.foodapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adaptar.MealPlannerAdapter
import com.example.foodapp.Adaptar.RecipesAdapter
import com.example.foodapp.DataClass.MealPlannerDataClass
import com.example.foodapp.DataClass.Recipe
import com.example.foodapp.Interfaces.FabActionHandlerMealPlanner
import com.example.foodapp.Interfaces.FabActionHandlerRecipes
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMealPlannerBinding
import com.example.foodapp.databinding.FragmentRecipiesBinding
import java.util.Calendar


class MealPlanner : Fragment(), FabActionHandlerMealPlanner {


    private var _binding: FragmentMealPlannerBinding? = null
    private val binding get() = _binding!!
    private lateinit var MealPlannerAdapter: MealPlannerAdapter

    // ArrayList to hold Recipe objects
    private val mealPlannerList = ArrayList<MealPlannerDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealPlannerBinding.inflate(inflater, container, false)

        // Initialize the ArrayList with sample data
        initializeMealList()
        MealPlannerAdapter = MealPlannerAdapter(mealPlannerList)
        binding.rvMealPlanner.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MealPlannerAdapter
        }
        return binding.root
    }
    private fun initializeMealList() {
        mealPlannerList.add(createMealSchedule(2023, Calendar.JANUARY, 1, "New Year's Brunch"));
        mealPlannerList.add(createMealSchedule(2023, Calendar.FEBRUARY, 14, "Valentine's Dinner"));
        mealPlannerList.add(createMealSchedule(2023, Calendar.JULY, 4, "Independence Day BBQ"));
        mealPlannerList.add(createMealSchedule(2023, Calendar.OCTOBER, 31, "Halloween Party Snacks"));
        mealPlannerList.add(createMealSchedule(2023, Calendar.DECEMBER, 25, "Christmas Dinner"));
    }
    fun createMealSchedule(year: Int, month: Int, day: Int, meal: String): MealPlannerDataClass {
        val calendar = Calendar.getInstance().apply {
            set(year, month, day)
        }
        return MealPlannerDataClass(calendar, meal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFabClicked(mealPlan: MealPlannerDataClass) {
       mealPlannerList.add(mealPlan)
    }


}