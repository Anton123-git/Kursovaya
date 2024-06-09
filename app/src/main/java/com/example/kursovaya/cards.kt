package com.example.kursovaya

data class cards( val number: String = "",
                  val date: String = "",
                  val cvc: String? = null,
                  val index_card: Int)
{
    constructor() : this(" ", " ", "", 0) // Add a no-argument constructor
}