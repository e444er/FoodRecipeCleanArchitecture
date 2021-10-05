package com.droidios.foodrecipe.presentation.meal_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droidios.foodrecipe.R
import com.droidios.foodrecipe.databinding.ViewHolderSearchListBinding
import com.droidios.foodrecipe.domain.model.Meal

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.MyViewHolder>() {

    var onClickListener: ((Meal) -> Unit)? = null

    class MyViewHolder(val binding: ViewHolderSearchListBinding) :
        RecyclerView.ViewHolder(binding.root)


    private class DifferCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.mealId == newItem.mealId
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer<Meal>(this, DifferCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ViewHolderSearchListBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataId = differ.currentList[position]
        holder.binding.apply {
            viewHolderItemName.text = dataId.name
            Glide.with(root)
                .load(dataId.image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolderImage)
        }
        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(dataId)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}