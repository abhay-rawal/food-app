package com.example.foodapp.Adaptar

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodapp.Fragments.AboutMe
import com.example.foodapp.Fragments.Blog
import com.example.foodapp.Fragments.Contact
import com.example.foodapp.Fragments.MealPlanner
import com.example.foodapp.Fragments.Recipes
import java.lang.ref.WeakReference

class MyAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {
    private val fragmentsMap = SparseArray<WeakReference<Fragment>>()

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> Recipes()
            1 -> MealPlanner()
            2 -> Blog()
            3 -> Contact()
            4 -> AboutMe()
            else -> Recipes()
        }
        fragmentsMap.put(position, WeakReference(fragment))
        return fragment
    }

    fun getFragment(position: Int): Fragment? {
        return fragmentsMap.get(position)?.get()
    }
}
