package com.example.kursovaya

data class User(
    val name: String = "",
    val phone: String = "",
    val userID: String? = null,
    val status: Int
) {
    constructor() : this("", "", null, 0) // Add a no-argument constructor
}