package com.example.a2023r2022cs203_midproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OutstandingAdapter(private val list: List<Payment>) : RecyclerView.Adapter<OutstandingAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvOfficerName: TextView = view.findViewById(R.id.tvOfficerName)
        val tvAmount: TextView = view.findViewById(R.id.tvOutstandingAmount)
        val tvDetails: TextView = view.findViewById(R.id.tvOfficerDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_outstanding, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvOfficerName.text = item.salesOfficerName
        holder.tvAmount.text = "₹${item.amount.toInt()}"
        holder.tvDetails.text = "Total Sales: ₹${(item.amount + 2000).toInt()} | Paid: ₹2000"
    }

    override fun getItemCount() = list.size
}
