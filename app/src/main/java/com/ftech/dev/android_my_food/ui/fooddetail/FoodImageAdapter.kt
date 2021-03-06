package com.ftech.dev.android_my_food.ui.fooddetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ftech.dev.android_my_food.R
import com.ftech.dev.android_my_food.databinding.ItemFoodImageBinding

class FoodImageAdapter : RecyclerView.Adapter<FoodImageAdapter.FoodImageViewHolder>() {
    var list: MutableList<Int> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var callBack: FoodImageListener? = null

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodImageViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFoodImageBinding>(
            layoutInflater,
            R.layout.item_food_image,
            parent,
            false
        )
        return FoodImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodImageViewHolder, position: Int) {
        val item = list[position]
        holder.binding.item = item
        holder.binding.itemPosition = position
        holder.binding.itemListener = callBack
    }

    class FoodImageViewHolder(val binding: ItemFoodImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    interface FoodImageListener {
        fun onItemClick(index: Int, item: Int)
    }

}