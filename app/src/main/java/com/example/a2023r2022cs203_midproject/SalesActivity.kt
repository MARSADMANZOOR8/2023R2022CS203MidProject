package com.example.a2023r2022cs203_midproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class SalesActivity : AppCompatActivity() {

    private lateinit var salesAdapter: SalesAdapter
    private var allSales = mutableListOf<Sale>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Sales"

        setupRecyclerView()
        setupSearch()
        setupBottomNavigation()
        loadSampleData()

        findViewById<Button>(R.id.btnNewSale).setOnClickListener {
            startActivity(Intent(this, CreateSaleActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_sales
        
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
                R.id.nav_sales -> true
                R.id.nav_payments -> {
                    startActivity(Intent(this, PaymentsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_reports -> {
                    Toast.makeText(this, "Reports module coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSales)
        salesAdapter = SalesAdapter(allSales)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = salesAdapter
    }

    private fun setupSearch() {
        val etSearch = findViewById<EditText>(R.id.etSearchSales)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterSales(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterSales(query: String) {
        val filteredList = allSales.filter {
            it.customerName.contains(query, ignoreCase = true) || 
            it.invoiceId.contains(query, ignoreCase = true)
        }
        salesAdapter.updateData(filteredList)
    }

    private fun loadSampleData() {
        allSales.add(Sale("INV001", "John Doe", "9876543210", "2025-01-27", 1, 3500.0, 0.0, "Paid"))
        allSales.add(Sale("INV002", "Jane Smith", "9876543211", "2025-01-27", 2, 3050.0, 1050.0, "Partial"))
        allSales.add(Sale("INV003", "Robert Brown", "9876543212", "2025-01-26", 3, 5200.0, 5200.0, "Overdue"))
        salesAdapter.updateData(allSales)
    }
}
