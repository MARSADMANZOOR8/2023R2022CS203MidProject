package com.example.a2023r2022cs203_midproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class PaymentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Payments"

        setupRecyclerViews()
        setupBottomNavigation()

        findViewById<MaterialButton>(R.id.btnRecordPayment).setOnClickListener {
            startActivity(Intent(this, RecordPaymentActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_payments
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_inventory -> {
                    startActivity(Intent(this, InventoryActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_sales -> {
                    startActivity(Intent(this, SalesActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_payments -> true
                R.id.nav_reports -> {
                    Toast.makeText(this, "Reports module coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
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
    }
}
