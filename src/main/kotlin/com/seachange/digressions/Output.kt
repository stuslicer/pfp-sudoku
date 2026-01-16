package com.seachange.com.seachange.digressions

import com.seachange.sudoku.Grid
import com.seachange.sudoku.createGrid
import java.io.PrintStream


fun main() {
    val grid = createGrid()
    println(grid)
    System.out.println(grid)
    println(grid.hashCode().toHexString())
    println(grid.contentDeepToString())
    println(grid.joinToString(separator = "\n") { it.joinToString() })
}

fun outputGrid(grid: Grid, output: PrintStream) {
    output.println(grid.contentDeepToString())
    output.append(grid.contentDeepToString())
}

