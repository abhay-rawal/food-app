package com.example.foodapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.Adaptar.AboutMeAdaptar
import com.example.foodapp.DataClass.AboutMeDataClass
import com.example.foodapp.Interfaces.FabActionHandlerAboutMe
import com.example.foodapp.databinding.FragmentAboutMeBinding


class AboutMe : Fragment(), FabActionHandlerAboutMe {

    private var _binding: FragmentAboutMeBinding? = null
    private val binding get() = _binding!!
    private lateinit var aboutMeAdaptar: AboutMeAdaptar

    // ArrayList to hold Recipe objects
    private val aboutMeList = ArrayList<AboutMeDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)

        // Initialize the ArrayList with sample data
        initializeAboutMeList()
        aboutMeAdaptar = AboutMeAdaptar(aboutMeList)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = aboutMeAdaptar
        }
        return binding.root
    }
    private fun initializeAboutMeList() {
        aboutMeList.add(AboutMeDataClass("Name", "Abhay Rawal"))
        aboutMeList.add(AboutMeDataClass("Culinary journey", "Began exploring diverse cuisines at a young age, inspired by travels and cultural heritage. Passionate about sustainable cooking."))
        aboutMeList.add(AboutMeDataClass("Favorite recipes", "Signature dishes include Mediterranean Quinoa Salad, Spicy Thai Curry, and Classic French Tart Tatin. Enjoy experimenting with fusion recipes."))
        aboutMeList.add(AboutMeDataClass("Food philosophy", "Advocate for farm-to-table eating. Belief in balancing taste, health, and environmental sustainability in every meal. Less is more in seasoning."))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFabClicked(aboutMe: AboutMeDataClass) {
        aboutMeList.add(aboutMe);
    }


}