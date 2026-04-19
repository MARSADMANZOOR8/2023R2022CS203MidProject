package com.example.a2023r2022cs203_midproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateSaleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_sale)

        // Close button logic
        findViewById<ImageView>(R.id.btnClose).setOnClickListener {
            finish()
        }

        setupDropdowns()

        findViewById<Button>(R.id.btnAddToSale).setOnClickListener {
            val pesticide = findViewById<AutoCompleteTextView>(R.id.spinnerPesticide).text.toString()
            if (pesticide.isNotEmpty()) {
                Toast.makeText(this, "$pesticide added to sale list", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please select a pesticide", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnCreateSale).setOnClickListener {
            val officer = findViewById<AutoCompleteTextView>(R.id.spinnerSalesOfficer).text.toString()
            if (officer.isEmpty()) {
                Toast.makeText(this, "Please select a Sales Officer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // Logic to save the sale would go here
            Toast.makeText(this, "Sale Created & Invoice Generated", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setupDropdowns() {
        // Sales Officers
        val officers = arrayOf("Ali Khan", "Rahul Sharma", "Zaid Ahmed", "Priya Singh")
        val officerAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, officers)
        findViewById<AutoCompleteTextView>(R.id.spinnerSalesOfficer).setAdapter(officerAdapter)

        // Payment Modes
        val paymentModes = arrayOf("Cash", "Online", "Credit", "Cheque")
        val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, paymentModes)
        findViewById<AutoCompleteTextView>(R.id.spinnerPaymentMode).setAdapter(paymentAdapter)

        // Pesticides
        val pesticides = arrayOf(
            "ITM001 - Chlorpyrifos",
            "ITM002 - Glyphosate",
            "ITM003 - Delta 50",
            "ITM004 - Malathion",
            "ITM005 - Neem Oil"
        )
        val pesticideAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, pesticides)
        findViewById<AutoCompleteTextView>(R.id.spinnerPesticide).setAdapter(pesticideAdapter)
    }
}
