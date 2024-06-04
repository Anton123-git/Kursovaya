package com.example.kursovaya

data class ItemHistory(
    val idPayment: Int,
    val indexPay: Boolean,
    val sum: Double,
    val title: String,
    val date: String
)
