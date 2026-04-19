package com.example.a2023r2022cs203_midproject

data class Sale(
    val invoiceId: String,
    val customerName: String,
    val customerPhone: String,
    val date: String,
    val itemCount: Int,
    val totalAmount: Double,
    val amountDue: Double,
    val status: String // "Paid", "Partial", "Overdue"
)
