package com.example.kursovaya

data class CourierAllOerdersItem(
    val userID: Int,
    val orderStatus: Int,
    val idPayment: Int,
    val nameOrder: String,
    val title: String,
    val date: String
)
