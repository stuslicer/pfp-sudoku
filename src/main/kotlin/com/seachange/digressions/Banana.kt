package com.seachange.com.seachange.digressions

import com.seachange.sudoku.GRID_SIZE
import com.seachange.sudoku.createGrid

class Banana(val weightInGrams: Int) {

    operator fun compareTo(other: Banana): Int {
        return weightInGrams.compareTo(other.weightInGrams)
    }
}

fun main(args: Array<String>) {
    val smallBanana = Banana(80)
    val largeBanana = Banana(140)

    if( largeBanana > smallBanana ) {
        println("Large banana is larger than small banana")
    }

    val grid = createGrid()

    grid.get(0, 1)
    grid.set(0, 1, 4)

    grid[0,1]
    grid[1,0] = 4
}