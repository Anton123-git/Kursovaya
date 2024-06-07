package com.example.kursovaya

data class Payment(
    val index_pay: Int,
    val amount: Double,
    val title: String,
    val date: String,
    val uid: String
) {
    constructor() : this(0, 0.0, "", "", "") // Add a no-argument constructor
}
