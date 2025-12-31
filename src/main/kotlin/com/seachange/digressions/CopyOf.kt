package com.seachange.com.seachange.digressions

import com.seachange.sudoku.createGrid

fun main() {
    val gridA = createGrid()
    val gridB = gridA.copyOf()

    println(gridA.contentDeepEquals(gridB)) // true
    println(gridA !== gridB) // true

    gridA[0][0] = 42
    println(gridB[0][0]) // 42 !!
}