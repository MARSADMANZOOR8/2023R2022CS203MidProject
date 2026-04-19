package com.example.a2023r2022cs203_midproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class InventoryAdapter(private var items: List<InventoryItem>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvItemName)
        val tvId: TextView = view.findViewById(R.id.tvItemId)
        val tvCategory: TextView = view.findViewById(R.id.tvItemCategory)
        val tvStock: TextView = view.findViewById(R.id.tvItemStock)
        val tvPrice: TextView = view.findViewById(R.id.tvItemPrice)
        val ivEdit: ImageView = view.findViewById(R.id.ivEdit)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inventory, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvId.text = item.id
        holder.tvCategory.text = "${item.category} (${item.subCategory})"
        holder.tvStock.text = "${item.stockQuantity} ${item.unit}"
        holder.tvPrice.text = "Rs ${item.price}/${item.unit}"
        
        // Handle low stock color
        if (item.stockQuantity < 5) {
            holder.tvStock.setTextColor(holder.itemView.context.getColor(R.color.error_red))
        } else {
            holder.tvStock.setTextColor(holder.itemView.context.getColor(R.color.primary_green))
        }

        holder.ivEdit.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Edit: ${item.name}", Toast.LENGTH_SHORT).show()
        }

        holder.ivDelete.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Delete: ${item.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<InventoryItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
