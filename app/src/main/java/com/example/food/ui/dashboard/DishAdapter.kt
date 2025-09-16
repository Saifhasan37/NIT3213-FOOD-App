package com.example.food.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.ItemDishBinding
import com.example.food.domain.model.Dish

class DishAdapter(
    private val onItemClick: (Dish) -> Unit
) : ListAdapter<Dish, DishAdapter.DishViewHolder>(DishDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = ItemDishBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DishViewHolder(
        private val binding: ItemDishBinding,
        private val onItemClick: (Dish) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dish) {
            binding.dishNameTextView.text = dish.dishName
            binding.originTextView.text = dish.origin
            binding.mealTypeChip.text = dish.mealType
            
            binding.root.setOnClickListener {
                onItemClick(dish)
            }
        }
    }

    class DishDiffCallback : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem.dishName == newItem.dishName
        }

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
            return oldItem == newItem
        }
    }
}

