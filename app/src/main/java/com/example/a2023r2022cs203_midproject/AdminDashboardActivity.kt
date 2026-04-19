package com.example.a2023r2022cs203_midproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class AdminDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Smart Agro Chemical"

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Modern way to handle back press
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        // Setup Card Clicks for the Dashboard
        setupCardListeners()
    }

    private fun setupCardListeners() {
        findViewById<CardView>(R.id.cardInventory).setOnClickListener {
            startActivity(Intent(this, InventoryActivity::class.java))
        }
        findViewById<CardView>(R.id.cardSales).setOnClickListener {
            startActivity(Intent(this, SalesActivity::class.java))
        }
        findViewById<CardView>(R.id.cardCustomers).setOnClickListener {
            startActivity(Intent(this, PaymentsActivity::class.java))
        }
        findViewById<CardView>(R.id.cardReports).setOnClickListener {
            Toast.makeText(this, "Reports module coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                // Already on dashboard
            }
            R.id.nav_inventory -> {
                startActivity(Intent(this, InventoryActivity::class.java))
            }
            R.id.nav_sales -> {
                startActivity(Intent(this, SalesActivity::class.java))
            }
            R.id.nav_payments -> {
                startActivity(Intent(this, PaymentsActivity::class.java))
            }
            R.id.nav_customers -> {
                Toast.makeText(this, "Customers module coming soon", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_reports -> {
                Toast.makeText(this, "Reports module coming soon", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
