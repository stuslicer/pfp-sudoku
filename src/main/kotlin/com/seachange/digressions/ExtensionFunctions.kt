package com.seachange.com.seachange.digressions


fun isStringPalindrome(s: String) = s == s.reversed()
fun String.isPalindrome() = this == this.reversed()

fun main() {
    isStringPalindrome("ABBA")
    "ABBA".isPalindrome()
}