package com.example.kursovaya

data class AdminAllOrderItem(
    val id_order: Int,
    val id_user: Int,
    val a_address: String,
    val a_state: String,
    val a_phone: String,
    val a_others: String,
    val b_address: String,
    val b_state: String,
    val b_phone: String,
    val b_others: String,
    val package_items: String,
    val weight: String,
    val worth: String,
    val date: String,
    val residence_status: Int,
    val c_name: String
)

