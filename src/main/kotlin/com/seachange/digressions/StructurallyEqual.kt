package com.seachange.com.seachange.digressions

fun main() {
    val list = listOf(1, 2, 3)
    val list2 = listOf(1, 2, 3)
    val list3 = listOf(4, 5, 6)

    println(list.equals(list2)) // true
    println(list == list2) // true
    println(list === list2) // false

    println(list.equals(list3)) // false
    println(list == list3) // false
}