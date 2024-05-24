package com.example.myindapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myindapp.databinding.ItemStoreBinding
import com.example.myindapp.data.Store

class StoreAdapter(private val onClick: (Store) -> Unit) :
    ListAdapter<Store, StoreAdapter.StoreViewHolder>(StoreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = getItem(position)
        holder.bind(store)
    }

    class StoreViewHolder(private val binding: ItemStoreBinding, val onClick: (Store) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            binding.storeName.text = store.name
            binding.storeAddress.text = store.address
            binding.root.setOnClickListener {
                onClick(store)
            }
        }
    }

    class StoreDiffCallback : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }
    }
}