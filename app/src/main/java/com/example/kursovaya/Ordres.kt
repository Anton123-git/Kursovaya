package com.example.kursovaya

data class Ordres(
    val userID: Int,
    val orderStatus: Int,
    val idPayment: Int,
    val nameOrder: String,
    val title: String,
    val date: String
)
