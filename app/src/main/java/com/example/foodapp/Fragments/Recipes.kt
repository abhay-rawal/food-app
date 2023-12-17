package com.example.foodapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adaptar.RecipesAdapter
import com.example.foodapp.DataClass.Recipe
import com.example.foodapp.Interfaces.FabActionHandlerRecipes
import com.example.foodapp.databinding.FragmentRecipiesBinding

class Recipes : Fragment(),FabActionHandlerRecipes {

    private var _binding: FragmentRecipiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var recipesAdapter: RecipesAdapter

    // ArrayList to hold Recipe objects
    private val recipesList = ArrayList<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipiesBinding.inflate(inflater, container, false)

        // Initialize the ArrayList with sample data
        initializeRecipesList()
        recipesAdapter = RecipesAdapter(recipesList)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
        return binding.root
    }


    private fun initializeRecipesList() {
        // Add some sample data to the list
        recipesList.add(Recipe("Chocolate Cake", "Flour, sugar, cocoa powder, baking powder, eggs, milk, oil", "Mix ingredients, bake for 30 minutes at 350 degrees",
            null,4.5f))
        // Add more recipes as needed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFabClicked(recipe: Recipe) {
        recipesList.add(recipe)
    }

}

