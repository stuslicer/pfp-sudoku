package com.seachange.digressions

fun main() {
    val a = arrayOf(1,2,3)
    println(a.contentToString())
    a[2] = 4
    println(a.contentToString())
    val b = Array(10) { 0 }
    val c = Array(10) { it }
    val d = Array(10) { Array(10) { 0 } }
    println(b.contentToString())
    println(c.contentToString())
    println(d.contentDeepToString())
}