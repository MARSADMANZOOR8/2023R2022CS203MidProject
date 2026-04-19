package com.example.a2023r2022cs203_midproject

data class Payment(
    val invoiceId: String,
    val salesOfficerName: String,
    val amount: Double,
    val date: String,
    val paymentMethod: String,
    val type: String // "Received", "Outstanding"
)
