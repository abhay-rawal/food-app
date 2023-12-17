package com.example.foodapp.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.DataClass.AboutMeDataClass
import com.example.foodapp.databinding.AboutmeListBinding

class AboutMeAdaptar(private val aboutMeList: ArrayList<AboutMeDataClass>) : RecyclerView.Adapter<AboutMeAdaptar.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AboutmeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val aboutMe = aboutMeList[position]
        holder.binding.key.text = aboutMe.key
        holder.binding.value.text = aboutMe.value
    }


    override fun getItemCount(): Int {
        return aboutMeList.size
    }

    inner class MyViewHolder(val binding: AboutmeListBinding) : RecyclerView.ViewHolder(binding.root)
}