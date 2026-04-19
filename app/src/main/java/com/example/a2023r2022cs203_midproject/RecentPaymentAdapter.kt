package com.example.a2023r2022cs203_midproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecentPaymentAdapter(private val list: List<Payment>) : RecyclerView.Adapter<RecentPaymentAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvInvoiceId: TextView = view.findViewById(R.id.tvInvoiceId)
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvOfficerName: TextView = view.findViewById(R.id.tvOfficerName)
        val tvDetails: TextView = view.findViewById(R.id.tvPaymentDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvInvoiceId.text = item.invoiceId
        holder.tvAmount.text = "₹${item.amount.toInt()}"
        holder.tvOfficerName.text = item.salesOfficerName
        holder.tvDetails.text = "${item.paymentMethod} • ${item.date}"
    }

    override fun getItemCount() = list.size
}
