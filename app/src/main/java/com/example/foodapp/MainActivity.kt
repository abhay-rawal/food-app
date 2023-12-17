package com.example.foodapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.Adaptar.MyAdapter
import com.example.foodapp.DataClass.AboutMeDataClass
import com.example.foodapp.DataClass.BlogDataClass
import com.example.foodapp.DataClass.MealPlannerDataClass
import com.example.foodapp.DataClass.Recipe
import com.example.foodapp.Interfaces.FabActionHandlerAboutMe
import com.example.foodapp.Interfaces.FabActionHandlerBlogs
import com.example.foodapp.Interfaces.FabActionHandlerMealPlanner
import com.example.foodapp.Interfaces.FabActionHandlerRecipes
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.databinding.LayoutDialogueAboutmeBinding
import com.example.foodapp.databinding.LayoutDialogueBlogBinding
import com.example.foodapp.databinding.LayoutDialogueRecipeBinding
import com.example.foodapp.databinding.LayoutMealplannerDialogueBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        val adapter = MyAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Recipes"
                1 -> tab.text = "Meal Planner"
                2 -> tab.text = "Blog"
                3 -> tab.text = "Contact"
                4 -> tab.text = "About Me"
            }
        }.attach()

        binding.fab.setOnClickListener {
            when (binding.viewPager.currentItem) {
                0 -> RecepieFabHandler(binding)
                1 -> MealFabHandler(binding)
                2 -> BlogFabHandler(binding)
                3 -> Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show()
                4 -> AboutMeFabHandler(binding)
            }
        }
    }

    private fun AboutMeFabHandler(mainBinding: ActivityMainBinding) {
        val binding = LayoutDialogueAboutmeBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("OK") { _, _ ->
                val aboutMe = AboutMeDataClass(
                    binding.detailTitle.text.toString(),
                    binding.detailBody.text.toString()
                )
                val currentFragment = (mainBinding.viewPager.adapter as MyAdapter).getFragment(mainBinding.viewPager.currentItem)
                if (currentFragment is FabActionHandlerAboutMe) {
                    currentFragment.onFabClicked(aboutMe)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun BlogFabHandler(mainBinding: ActivityMainBinding) {
        val binding = LayoutDialogueBlogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("OK") { _, _ ->
                val blog = BlogDataClass(
                    null,
                    null,
                    binding.blogTitle.text.toString(),
                    binding.blogBody.text.toString()
                )
                val currentFragment = (mainBinding.viewPager.adapter as MyAdapter).getFragment(mainBinding.viewPager.currentItem)
                if (currentFragment is FabActionHandlerBlogs) {
                    currentFragment.onFabClicked(blog)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }


    private fun RecepieFabHandler(mainBinding: ActivityMainBinding) {
        val binding = LayoutDialogueRecipeBinding.inflate(layoutInflater)
        binding.selectImageButton.setOnClickListener {
            openGallery()
        }

        val dialog = AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("OK") { _, _ ->
                val selectedImageUri = getImageUriFromSharedPreferences()
                val recipe = Recipe(
                    binding.recipeName.text.toString(),
                    binding.ingredientName.text.toString(),
                    binding.instructionName.text.toString(),
                    selectedImageUri, // Use the URI here
                    binding.rating.text.toString().toFloat()
                )
                val currentFragment = (mainBinding.viewPager.adapter as MyAdapter).getFragment(mainBinding.viewPager.currentItem)
                if (currentFragment is FabActionHandlerRecipes) {
                    currentFragment.onFabClicked(recipe)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            selectedImageUri?.let {
                saveImageUriToSharedPreferences(it)
            }
        }
    }

    private fun saveImageUriToSharedPreferences(uri: Uri) {
        val sharedPreferences = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("imageUri", uri.toString())
        editor.apply()
    }

    private fun getImageUriFromSharedPreferences(): Uri? {
        val sharedPreferences = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("imageUri", null)?.let { Uri.parse(it) }
    }

    private fun MealFabHandler(mainBinding: ActivityMainBinding) {
        // Inflate using view binding
        val binding = LayoutMealplannerDialogueBinding.inflate(layoutInflater)
        var selectedDateFromCalender: Calendar? = null

        binding.selectDate.setOnClickListener {
            // Get the current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog and show it
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // This is called when a date is selected
                val selectedDate = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDayOfMonth)
                }
                selectedDateFromCalender = selectedDate
                binding.selectDate.text = selectedDate.time.toString()
                // Update the button text or any other UI element to show selected date
            }, year, month, day)

            datePickerDialog.show()
        }

        val dialog = AlertDialog.Builder(this)
            .setView(binding.root)
            .setPositiveButton("OK") { dialog, which ->
                if (selectedDateFromCalender != null) {
                    val meal = binding.mealName.text.toString()
                    val newMealPlan = MealPlannerDataClass(
                        date = selectedDateFromCalender!!,
                        meal = meal
                    )

                    val currentItem = mainBinding.viewPager.currentItem
                    val currentFragment = (mainBinding.viewPager.adapter as MyAdapter).getFragment(currentItem)
                    if (currentFragment is FabActionHandlerMealPlanner) {
                        currentFragment.onFabClicked(newMealPlan)
                    }
                } else {
                    Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }



    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.receipe -> binding.viewPager.currentItem = 0
                R.id.meal_planner -> binding.viewPager.currentItem = 1
                R.id.blog -> binding.viewPager.currentItem = 2
            }
            true
        }
    }
}