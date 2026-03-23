package com.seachange.digressions

class DestructuringDeclarations {
}

fun main() {
    val list = listOf(1, 2, 3)
    val (first, second, third) = list

    val location = 4 to 2
    val (row, column) = location
}