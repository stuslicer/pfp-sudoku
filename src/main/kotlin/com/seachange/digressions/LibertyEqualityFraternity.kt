package com.seachange.digressions

fun main() {
    val a = listOf(1,2,3)
    val b = listOf(1,2,3)
    println(a == b)
    val c = mutableSetOf(1,2,3)
    val d = mutableSetOf(1,2,3).apply { add(4) }
    println(c == d)
}

