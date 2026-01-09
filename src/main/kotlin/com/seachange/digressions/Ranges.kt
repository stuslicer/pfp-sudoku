package com.seachange.com.seachange.digressions


val cellRange: IntRange = 0..9

fun Int.isValidCellValue() = this in cellRange

fun main() {
    printOut(1..10) // 1 2 3 4 5 6 7 8 9 10

    printOut(1..<10) // 1 2 3 4 5 6 7 8 9
    printOut(1 until 10) // 1 2 3 4 5 6 7 8 9

    printOut(1 .. 10 step 2) // 1 3 5 7 9
    printOut(10 downTo 1) // 10 9 8 7 6 5 4 3 2 1
    printOut(10 downTo 1 step 2) // 10 8 6 4 2

    if( 7 in cellRange) {
        println("In range")
    }

    if( 7.isValidCellValue() ) {
        println("Is valid")
    }
    if( 10.isValidCellValue() ) {
        println("Is valid")
    }

    1 until 10
    1.until(10)

}

fun printOut(progression: IntProgression) {
    for(i in progression) {
        print("$i ")
    }
    println()
    println(progression.toString())
}

