package com.example.a2023r2022cs203_midproject

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout

class InventoryActivity : AppCompatActivity() {

    private lateinit var adapter: InventoryAdapter
    private val inventoryList = mutableListOf<InventoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Inventory"

        setupRecyclerView()
        setupSearch()
        setupTabs()
        setupBottomNavigation()
        loadDummyData()

        findViewById<ExtendedFloatingActionButton>(R.id.fabAddProduct).setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnBulkUpdate).setOnClickListener {
            Toast.makeText(this, "Bulk Update feature coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_inventory
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    startActivity(Intent(this, AdminDashboardActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_inventory -> true
                R.id.nav_sales -> {
                    startActivity(Intent(this, SalesActivity::class.java))
                    finish()
                    true
                }
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
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewInventory)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = InventoryAdapter(inventoryList)
        recyclerView.adapter = adapter
    }

    private fun setupSearch() {
        val etSearch: EditText = findViewById(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(text: String) {
        val filteredList = if (text.isEmpty()) {
            inventoryList
        } else {
            inventoryList.filter {
                it.name.contains(text, ignoreCase = true) ||
                        it.id.contains(text, ignoreCase = true) ||
                        it.category.contains(text, ignoreCase = true)
            }
        }
        adapter.updateData(filteredList)
        findViewById<TextView>(R.id.tvListCount).text = "Product List (${filteredList.size})"
    }

    private fun setupTabs() {
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        findViewById<View>(R.id.recyclerViewInventory).visibility = View.VISIBLE
                        findViewById<TextView>(R.id.tvListCount).text = "Product List (${inventoryList.size})"
                    }
                    1 -> {
                        findViewById<View>(R.id.recyclerViewInventory).visibility = View.GONE
                        findViewById<TextView>(R.id.tvListCount).text = "No Stock Movements"
                        Toast.makeText(this@InventoryActivity, "Stock movements feature coming soon", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun loadDummyData() {
        inventoryList.clear()
        inventoryList.add(InventoryItem("ITM001", "Paraquat Dichloride", "Pesticide", "Herbicide", 15.0, "Ltr", 1200.0))
        inventoryList.add(InventoryItem("ITM002", "Imidacloprid 20% SL", "Pesticide", "Insecticide", 3.0, "Ltr", 2500.0))
        inventoryList.add(InventoryItem("ITM003", "Zinc Sulfate 33%", "Fertilizer", "Micronutrient", 50.0, "Kg", 450.0))
        inventoryList.add(InventoryItem("ITM004", "Glyphosate 41% SL", "Pesticide", "Herbicide", 0.0, "Ltr", 1800.0))
        inventoryList.add(InventoryItem("ITM005", "DAP Fertilizer", "Fertilizer", "Macro", 100.0, "Bag", 12500.0))
        
        findViewById<TextView>(R.id.tvListCount).text = "Product List (${inventoryList.size})"
        adapter.notifyDataSetChanged()
    }
}
