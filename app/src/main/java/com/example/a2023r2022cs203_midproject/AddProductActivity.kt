package com.example.a2023r2022cs203_midproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        setupSpinners()

        findViewById<Button>(R.id.btnSaveProduct).setOnClickListener {
            // Logic to save product would go here
            Toast.makeText(this, "Product Saved Successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupSpinners() {
        val categories = arrayOf("Pesticide", "Fertilizer", "Seeds", "Equipment")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        findViewById<AutoCompleteTextView>(R.id.spinnerCategory).setAdapter(categoryAdapter)

        val subCategories = arrayOf("Fungicide", "Herbicide", "Insecticide", "Rodenticide", "Macro Nutrient", "Micro Nutrient")
        val subCategoryAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subCategories)
        findViewById<AutoCompleteTextView>(R.id.spinnerSubCategory).setAdapter(subCategoryAdapter)

        val units = arrayOf("Ltr", "Kg", "Bag", "Pcs", "Pack")
        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, units)
        findViewById<AutoCompleteTextView>(R.id.spinnerUnit).setAdapter(unitAdapter)
    }
}
