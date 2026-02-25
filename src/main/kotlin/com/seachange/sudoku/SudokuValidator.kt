package com.seachange.sudoku

class SudokuValidator(
    private val grid: Grid
) {

    internal fun isRowValid(row: Int): Boolean {
        val values = (0..<GRID_SIZE).map { columnIndex ->
            grid[row, columnIndex]
        }.filter { it > 0 }

        val foundValues = mutableSetOf<Int>()
        return values.all { foundValues.add(it) }
    }

}