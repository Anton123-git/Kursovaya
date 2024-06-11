package com.example.kursovaya

data class Ordres(
    val id_order: String = "",
    val id_user: String = "",
    val a_address: String = "",
    val a_state: String = "",
    val a_phone: String = "",
    val a_others: String = "",
    val b_address: String = "",
    val b_state: String = "",
    val b_phone: String = "",
    val b_others: String = "",
    val package_items: String = "",
    val weight: String = "",
    val worth: String = "",
    val date: String = "",
    val residence_status: Int = 0,
    val c_name: String = ""
)
