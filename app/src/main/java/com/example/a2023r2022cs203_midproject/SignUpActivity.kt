package com.example.a2023r2022cs203_midproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etEmail = findViewById<EditText>(R.id.etSignUpEmail)
        val etPassword = findViewById<EditText>(R.id.etSignUpPassword)
        val spinnerArea = findViewById<Spinner>(R.id.spinnerArea)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)

        // Area Names List
        val areas = arrayOf(
            "Sargodha", "Multan", "Gujranwala", "Sheikhupura", "Okara",
            "Sahiwal", "Rahim Yar Khan", "Bahawalpur", "Faisalabad", "Pakpattan"
        )

        // Setup Spinner Adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, areas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerArea.adapter = adapter

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val selectedArea = spinnerArea.selectedItem.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // In a real app, save to Firebase or Database here including selectedArea
                Toast.makeText(this, "Registration Successful!\nEmail: $email\nArea: $selectedArea", Toast.LENGTH_LONG).show()
                finish() // Go back to login
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        tvBackToLogin.setOnClickListener {
            finish()
        }
    }
}