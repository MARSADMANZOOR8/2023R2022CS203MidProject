package com.example.a2023r2022cs203_midproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.a2023r2022cs203_midproject.data.AppDatabase
import com.example.a2023r2022cs203_midproject.data.SalesOfficer
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etEmail = findViewById<TextInputEditText>(R.id.etSignUpEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etSignUpPassword)
        val autoArea = findViewById<AutoCompleteTextView>(R.id.autoCompleteArea)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)

        // Modern Area Names List
        val areas = arrayOf(
            "Sargodha", "Multan", "Gujranwala", "Sheikhupura", "Okara",
            "Sahiwal", "Rahim Yar Khan", "Bahawalpur", "Faisalabad", "Pakpattan"
        )

        // Setup Modern Dropdown Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, areas)
        autoArea.setAdapter(adapter)

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val selectedArea = autoArea.text.toString()

            if (email.isEmpty() || password.isEmpty() || selectedArea.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Local Database Registration
            val newOfficer = SalesOfficer(
                email = email,
                area = selectedArea
            )

            // Using lifecycleScope for background database operation
            lifecycleScope.launch {
                try {
                    val db = AppDatabase.getDatabase(this@SignUpActivity)
                    db.salesOfficerDao().insert(newOfficer)
                    
                    Toast.makeText(this@SignUpActivity, "Registration Successful!", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@SignUpActivity, "Registration Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tvBackToLogin.setOnClickListener {
            finish()
        }
    }
}