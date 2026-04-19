package com.example.a2023r2022cs203_midproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class QuickPaymentAdapter(private val list: List<Payment>) : RecyclerView.Adapter<QuickPaymentAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvInvoiceId: TextView = view.findViewById(R.id.tvInvoiceId)
        val tvOfficerName: TextView = view.findViewById(R.id.tvOfficerName)
        val tvDueAmount: TextView = view.findViewById(R.id.tvDueAmount)
        val btnRecordQuick: Button = view.findViewById(R.id.btnRecordQuick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quick_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvInvoiceId.text = item.invoiceId
        holder.tvOfficerName.text = item.salesOfficerName
        holder.tvDueAmount.text = "Due: ₹${item.amount.toInt()}"
        
        holder.btnRecordQuick.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Recording payment for ${item.invoiceId}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = list.size
}
