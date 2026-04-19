package com.example.a2023r2022cs203_midproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class PaymentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        setupRecyclerViews()

        findViewById<MaterialButton>(R.id.btnRecordPayment).setOnClickListener {
            startActivity(Intent(this, RecordPaymentActivity::class.java))
        }
    }

    private fun setupRecyclerViews() {
        // Sample Data
        val outstanding = listOf(
            Payment("INV002", "Jane Smith", 1050.0, "2025-01-27", "Cash", "Outstanding"),
            Payment("INV003", "Bob Johnson", 2600.0, "2025-01-25", "Bank Transfer", "Outstanding")
        )

        val recent = listOf(
            Payment("INV001", "John Doe", 3500.0, "2025-01-27", "Cash", "Received"),
            Payment("INV002", "Jane Smith", 2000.0, "2025-01-27", "Bank Transfer", "Received")
        )

        val rvOutstanding = findViewById<RecyclerView>(R.id.rvOutstandingBalances)
        rvOutstanding.layoutManager = LinearLayoutManager(this)
        rvOutstanding.adapter = OutstandingAdapter(outstanding)

        val rvRecent = findViewById<RecyclerView>(R.id.rvRecentPayments)
        rvRecent.layoutManager = LinearLayoutManager(this)
        rvRecent.adapter = RecentPaymentAdapter(recent)

        val rvQuick = findViewById<RecyclerView>(R.id.rvQuickPayments)
        rvQuick.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvQuick.adapter = QuickPaymentAdapter(outstanding)
    }
}
