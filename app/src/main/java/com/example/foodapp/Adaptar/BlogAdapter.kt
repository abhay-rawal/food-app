package com.example.foodapp.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.DataClass.BlogDataClass
import com.example.foodapp.databinding.BlogListBinding

class BlogAdapter(private val blogList: ArrayList<BlogDataClass>) : RecyclerView.Adapter<BlogAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = BlogListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val blog = blogList[position]
        holder.binding.title.text = blog.title
        holder.binding.body.text = blog.body
    }


    override fun getItemCount(): Int {
        return blogList.size
    }

    inner class MyViewHolder(val binding: BlogListBinding) : RecyclerView.ViewHolder(binding.root)
}