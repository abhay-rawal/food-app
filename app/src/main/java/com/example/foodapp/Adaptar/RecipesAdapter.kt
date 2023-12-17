package com.example.foodapp.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.DataClass.Recipe
import com.example.foodapp.R
import com.example.foodapp.databinding.RecepieListBinding

class RecipesAdapter(private val blist: ArrayList<Recipe>) : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecepieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = blist[position]
        holder.binding.title.text = recipe.title
        holder.binding.instruction.text = recipe.instruction
        holder.binding.ingredients.text = recipe.ingredients
        holder.binding.rating.rating = recipe.rating
        // Use Glide to load the image
        Glide.with(holder.itemView.context)
            .load(recipe.imageUri) // Assuming imageUri is a Uri or String
            .error(R.drawable.choco_cake) // Replace with your default drawable
            .into(holder.binding.image)
    }


    override fun getItemCount(): Int {
        return blist.size
    }

    inner class MyViewHolder(val binding: RecepieListBinding) : RecyclerView.ViewHolder(binding.root)
}
