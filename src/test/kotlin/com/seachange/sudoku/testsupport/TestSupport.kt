package com.seachange.sudoku.testsupport

import com.seachange.sudoku.GRID_SIZE
import com.seachange.sudoku.GridArray

fun solvedGrid() = arrayOf(
    // top
    intArrayOf(8, 4, 6,  7, 9, 1,  2, 3, 5),
    intArrayOf(1, 2, 5,  8, 4, 3,  9, 7, 6),
    intArrayOf(7, 3, 9,  5, 6, 2,  8, 1, 4),

    // middle
    intArrayOf(3, 5, 7,  2, 8, 4,  6, 9, 1),
    intArrayOf(6, 9, 4,  3, 1, 5,  7, 2, 8),
    intArrayOf(2, 1, 8,  9, 7, 6,  5, 4, 3),

    // bottom
    intArrayOf(4, 7, 1,  6, 2, 8,  3, 5, 9),
    intArrayOf(5, 8, 2,  1, 3, 9,  4, 6, 7),
    intArrayOf(9, 6, 3,  4, 5, 7,  1, 8, 2),
).copyGrid()

fun unsolvedGrid(): GridArray = arrayOf(
    // top
    intArrayOf(0, 0, 6,  0, 9, 0,  0, 0, 0),
    intArrayOf(0, 2, 0,  8, 4, 0,  9, 7, 0),
    intArrayOf(0, 0, 9,  0, 6, 0,  8, 1, 4),

    // middle
    intArrayOf(0, 0, 0,  2, 0, 4,  0, 0, 0),
    intArrayOf(6, 0, 0,  3, 1, 0,  0, 2, 8),
    intArrayOf(0, 0, 8,  9, 0, 0,  5, 0, 3),

    // bottom
    intArrayOf(0, 7, 1,  0, 0, 8,  3, 5, 0),
    intArrayOf(0, 8, 0,  0, 3, 0,  0, 0, 7),
    intArrayOf(0, 6, 3,  0, 0, 7,  1, 0, 0),
).copyGrid()

private fun GridArray.copyGrid(): GridArray {
    return Array(GRID_SIZE) { IntArray(GRID_SIZE) { 0 } }.apply {
        this@copyGrid.forEachIndexed { row, rowArray ->
            rowArray.forEachIndexed { column, value ->
                this[row][column] = value
            }
        }
    }
}

