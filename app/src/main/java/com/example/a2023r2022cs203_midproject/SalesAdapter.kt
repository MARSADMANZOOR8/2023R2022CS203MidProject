package com.example.a2023r2022cs203_midproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class SalesAdapter(private var salesList: List<Sale>) :
    RecyclerView.Adapter<SalesAdapter.SalesViewHolder>() {

    class SalesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvInvoiceId: TextView = view.findViewById(R.id.tvInvoiceId)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvCustomerInfo: TextView = view.findViewById(R.id.tvCustomerInfo)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvSummary: TextView = view.findViewById(R.id.tvSummary)
        val tvAmountDue: TextView = view.findViewById(R.id.tvAmountDue)
        val btnView: TextView = view.findViewById(R.id.btnView)
        val btnPrint: TextView = view.findViewById(R.id.btnPrint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sale, parent, false)
        return SalesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val sale = salesList[position]
        holder.tvInvoiceId.text = sale.invoiceId
        holder.tvStatus.text = sale.status
        holder.tvCustomerInfo.text = "Customer: ${sale.customerName} • ${sale.customerPhone}"
        holder.tvDate.text = "Date: ${sale.date}"
        holder.tvSummary.text = "Items: ${sale.itemCount} • Total: ₹${sale.totalAmount.toInt()}"

        if (sale.amountDue > 0) {
            holder.tvAmountDue.visibility = View.VISIBLE
            holder.tvAmountDue.text = "Amount Due: ₹${sale.amountDue.toInt()}"
        } else {
            holder.tvAmountDue.visibility = View.GONE
        }

        // Status styling
        when (sale.status.lowercase()) {
            "paid" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_paid)
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.primary_green))
            }
            "partial" -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_partial)
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.secondary_orange))
            }
            else -> {
                holder.tvStatus.setBackgroundResource(R.drawable.bg_status_overdue)
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.error_red))
            }
        }

        holder.btnView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Viewing invoice ${sale.invoiceId}", Toast.LENGTH_SHORT).show()
        }

        holder.btnPrint.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Printing invoice ${sale.invoiceId}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = salesList.size

    fun updateData(newList: List<Sale>) {
        salesList = newList
        notifyDataSetChanged()
    }
}
