package com.example.a2023r2022cs203_midproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecordPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_payment)

        findViewById<ImageView>(R.id.btnClose).setOnClickListener {
            finish()
        }

        setupPaymentMethodSpinner()

        findViewById<Button>(R.id.btnRecord).setOnClickListener {
            val invoice = findViewById<android.widget.EditText>(R.id.etInvoiceNumber).text.toString()
            val officer = findViewById<android.widget.EditText>(R.id.etOfficerName).text.toString()
            val amount = findViewById<android.widget.EditText>(R.id.etAmount).text.toString()

            if (invoice.isEmpty() || officer.isEmpty() || amount.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Logic to save payment
            Toast.makeText(this, "Payment for $invoice recorded successfully", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setupPaymentMethodSpinner() {
        val methods = arrayOf("Cash", "Online Transfer", "Cheque", "Credit")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, methods)
        findViewById<AutoCompleteTextView>(R.id.spinnerPaymentMethod).setAdapter(adapter)
    }
}
